package com.example.mynewsapplication.news.data.model

data class NewsResponse(
    val status: String? = null,
    val totalResults: String? = null,
    val articles: List<Article>? = null,
    val code: String? = null,
    val message: String? = null,
)