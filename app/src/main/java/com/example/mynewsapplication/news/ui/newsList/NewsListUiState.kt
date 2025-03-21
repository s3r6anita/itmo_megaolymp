package com.example.mynewsapplication.news.ui.newsList

import com.example.mynewsapplication.news.ui.model.NewsItemModel

data class NewsListUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val news: NewsItemModel = NewsItemModel(),
    val errorMsg: String? = null,
)