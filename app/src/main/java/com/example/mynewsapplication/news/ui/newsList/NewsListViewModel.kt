package com.example.mynewsapplication.news.ui.newsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapplication.core.core.ConnectivityRepository
import com.example.mynewsapplication.news.domain.usecase.GetNewsResult
import com.example.mynewsapplication.news.domain.usecase.GetNewsUseCse
import com.example.mynewsapplication.news.ui.model.NewsItemModel.Companion.toNewsItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsUseCse: GetNewsUseCse,
    connectivityRepository: ConnectivityRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewsListUiState())
    val uiState = _uiState.asStateFlow()

    val networkStatus = connectivityRepository.isConnectedFlow.distinctUntilChanged().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = true
    )

    init {
        getNews("ITMO")
    }

    fun getNews(searchText: String = "") = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true, isError = false) }
        when (val response = getNewsUseCse(searchText)) {
            is GetNewsResult.Success ->
                _uiState.update {
                    it.copy(
                        news = response.news.toNewsItemModel(),
                        isLoading = false,
                        isError = false
                    )
                }

            is GetNewsResult.Error -> {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        errorMsg = response.msg
                    )
                }
            }
        }
    }
}