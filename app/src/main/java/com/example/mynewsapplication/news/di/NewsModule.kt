package com.example.mynewsapplication.news.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.mynewsapplication.news.data.model.NewsResponse
import com.example.mynewsapplication.news.data.network.NewsApi
import com.example.mynewsapplication.news.data.repository.NewsRepositoryImpl
import com.example.mynewsapplication.news.domain.model.NewsEntity
import com.example.mynewsapplication.news.domain.repository.NewsRepository
import com.example.mynewsapplication.util.NewsResponseSerializer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_NEWS_URL = "https://newsapi.org/"

private val Context.newsDataStore: DataStore<NewsResponse> by dataStore(
    fileName = "news_storage.pb",
    serializer = NewsResponseSerializer
)

@Module
@InstallIn(SingletonComponent::class)
interface NewsModule {

    @Binds
    fun bindRepository(impl: NewsRepositoryImpl): NewsRepository

    companion object {
        @[Provides Singleton]
        fun provideDS(
            @ApplicationContext appContext: Context
        ) = appContext.newsDataStore

        @[Provides Singleton]
        fun provideNewsApi(okHttpClient: OkHttpClient): NewsApi {
            return Retrofit.Builder()
                .baseUrl(BASE_NEWS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(NewsApi::class.java)
        }


        @[Provides Singleton]
        fun provideOkHttpClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }
    }
}