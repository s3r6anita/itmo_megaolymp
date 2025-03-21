package com.example.mynewsapplication.news.domain.usecase

import com.example.mynewsapplication.news.domain.model.NewsEntity
import com.example.mynewsapplication.news.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCse @Inject constructor(
    private val newsRepository: NewsRepository,
) {
    suspend operator fun invoke(searchPhrase: String = ""): GetNewsResult {
        val news = try {
            newsRepository.getNews(searchPhrase)
        } catch (e: Exception) {
            return GetNewsResult.Error(e.message)
        }
        return GetNewsResult.Success(news)
    }
}

sealed interface GetNewsResult {
    data class Success(val news: NewsEntity) : GetNewsResult
    data class Error(val msg: String? = null) : GetNewsResult
}