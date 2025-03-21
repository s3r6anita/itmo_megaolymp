package com.example.mynewsapplication.news.data.datasource

import androidx.datastore.core.DataStore
import com.example.mynewsapplication.news.data.model.NewsResponse
import com.example.mynewsapplication.news.data.network.NewsApi
import com.example.mynewsapplication.util.AppExceptions
import com.example.mynewsapplication.util.call
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val SUCCESS_CODE = "ok"
private const val ERROR_CODE = "error"

data class NewsDataSource @Inject constructor(
    private val newsApi: NewsApi,
) {
    suspend fun getNews(search: String) = withContext(Dispatchers.IO) {
        newsApi.getNews(search).call {
            if (it.code == ERROR_CODE) {
                throw AppExceptions.TechnicalError(it.code + " " + it.message)
            } else {
                it
            }
        }
    }
}
