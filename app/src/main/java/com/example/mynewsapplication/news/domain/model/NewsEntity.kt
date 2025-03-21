package com.example.mynewsapplication.news.domain.model

data class NewsEntity(
    val status: String,
    val totalResults: String,
    val articles: List<ArticleEntity>,
)