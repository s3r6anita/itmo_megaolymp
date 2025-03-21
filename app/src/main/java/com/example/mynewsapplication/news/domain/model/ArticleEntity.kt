package com.example.mynewsapplication.news.domain.model

data class ArticleEntity(
    val source: SourceEntity? = null,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
)