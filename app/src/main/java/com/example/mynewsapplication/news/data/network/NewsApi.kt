package com.example.mynewsapplication.news.data.network

import com.example.mynewsapplication.news.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "a8ac599cc61243aa867b3d92d05ec906"

interface NewsApi {
    @GET("/v2/everything")
    suspend fun getNews(
        @Query("q") search: String = "",
        @Query("apiKey") apiKey: String = API_KEY,
    ): Response<NewsResponse>
}