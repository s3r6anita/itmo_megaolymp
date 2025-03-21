package com.example.mynewsapplication.news.domain.repository

import com.example.mynewsapplication.news.domain.model.NewsEntity

interface NewsRepository {
    suspend fun getNews(search: String): NewsEntity
}