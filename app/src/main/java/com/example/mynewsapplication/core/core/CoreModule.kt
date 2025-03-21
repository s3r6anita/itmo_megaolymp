package com.example.mynewsapplication.core.core

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {

    @Binds
    fun bindConnectivityRepository(impl: ConnectivityRepositoryImpl): ConnectivityRepository
}