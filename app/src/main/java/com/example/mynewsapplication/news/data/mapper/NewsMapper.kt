package com.example.mynewsapplication.news.data.mapper

import com.example.mynewsapplication.news.data.model.Article
import com.example.mynewsapplication.news.data.model.NewsResponse
import com.example.mynewsapplication.news.data.model.Source
import com.example.mynewsapplication.news.domain.model.ArticleEntity
import com.example.mynewsapplication.news.domain.model.NewsEntity
import com.example.mynewsapplication.news.domain.model.SourceEntity

fun NewsResponse.toNewsEntity() = NewsEntity(
    status = this.status.orEmpty(),
    totalResults = this.totalResults.orEmpty(),
    articles = this.articles?.map { it.toArticleEntity() }.orEmpty(),
)

private fun Article.toArticleEntity() = ArticleEntity(
    source = this.source?.toSourceEntity(),
    author = this.author.orEmpty(),
    title = this.title.orEmpty(),
    description = this.description.orEmpty(),
    url = this.url.orEmpty(),
    urlToImage = this.urlToImage.orEmpty(),
    publishedAt = this.publishedAt.orEmpty(),
    content = this.content.orEmpty(),
)

private fun Source.toSourceEntity() = SourceEntity(
    id = this.id.orEmpty(),
    name = this.name.orEmpty(),
)