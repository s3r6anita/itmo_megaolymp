package com.example.mynewsapplication.news.data.repository

import androidx.datastore.core.DataStore
import com.example.mynewsapplication.news.data.datasource.NewsDataSource
import com.example.mynewsapplication.news.data.mapper.toNewsEntity
import com.example.mynewsapplication.news.data.model.NewsResponse
import com.example.mynewsapplication.news.domain.model.NewsEntity
import com.example.mynewsapplication.news.domain.repository.NewsRepository
import com.example.mynewsapplication.util.AppExceptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDS: NewsDataSource,
    private val ds: DataStore<NewsResponse>,
) : NewsRepository {

    override suspend fun getNews(search: String): NewsEntity = withContext(Dispatchers.Default) {
        val news = runCatching { newsDS.getNews(search) }
        news.getOrNull()?.let {
            val new = it.toNewsEntity()
            ds.updateData { it }
            return@let new
        } ?: return@withContext ds.data.firstOrNull()?.toNewsEntity() ?: throw AppExceptions.TechnicalError()
    }
}