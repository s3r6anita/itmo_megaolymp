package com.example.mynewsapplication.news.ui.model

import com.example.mynewsapplication.news.domain.model.ArticleEntity
import com.example.mynewsapplication.news.domain.model.NewsEntity
import com.example.mynewsapplication.news.ui.model.ArticleItemModel.Companion.toArticleItemModel
import com.example.mynewsapplication.util.formatDate


class NewsItemModel(
    val totalResults: String = "",
    val articles: List<ArticleItemModel> = emptyList(),
) {
    companion object {
        fun NewsEntity.toNewsItemModel() = NewsItemModel(
            totalResults = this.totalResults,
            articles = this.articles.map { it.toArticleItemModel() },
        )
    }
}

class ArticleItemModel(
    val title: String,
    val publishedAt: String,
    val urlToImage: String,
) {
    companion object {
        fun ArticleEntity.toArticleItemModel() = ArticleItemModel(
            title = this.title,
            publishedAt = formatDate(this.publishedAt),
            urlToImage = urlToImage,
        )
    }
}
