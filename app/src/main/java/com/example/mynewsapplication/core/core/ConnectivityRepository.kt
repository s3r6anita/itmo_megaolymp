package com.example.mynewsapplication.core.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

sealed class ConnectivityStatus {
    data object Connected : ConnectivityStatus()
    data object Disconnected : ConnectivityStatus()
}

interface ConnectivityRepository {
    val isConnectedFlow: Flow<ConnectivityStatus>
}

class ConnectivityRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ConnectivityRepository {

    private var manager: ConnectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE,
    ) as ConnectivityManager

    override val isConnectedFlow: Flow<ConnectivityStatus>
        get() = callbackFlow {
            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    manager.getNetworkCapabilities(network)?.let {
                        if (it.hasCapability(NET_CAPABILITY_INTERNET)) {
                            trySend(ConnectivityStatus.Connected)
                        }
                    }
                }

                override fun onLost(network: Network) {
                    trySend(ConnectivityStatus.Disconnected)
                }

                override fun onUnavailable() {
                    trySend(ConnectivityStatus.Disconnected)
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    capabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, capabilities)
                    if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                        trySend(ConnectivityStatus.Connected)
                    } else {
                        trySend(ConnectivityStatus.Disconnected)
                    }
                }
            }
            val networkRequest = NetworkRequest.Builder()
                .addCapability(NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build()

            manager.registerNetworkCallback(networkRequest, networkCallback)

            awaitClose {
                manager.unregisterNetworkCallback(networkCallback)
            }
        }
}