package com.example.mynewsapplication.util

import androidx.datastore.core.Serializer
import com.example.mynewsapplication.news.data.model.NewsResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.StandardCharsets

object NewsResponseSerializer : Serializer<NewsResponse> {
    override val defaultValue: NewsResponse
        get() = NewsResponse()

    override suspend fun readFrom(input: InputStream): NewsResponse {
        return try {
            Gson().fromJson(input.readBytes().decodeToString(), NewsResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: NewsResponse, output: OutputStream) {
        val jsonString = Gson().toJson(t)
        withContext(Dispatchers.IO) {
            output.write(jsonString.toByteArray(StandardCharsets.UTF_8))
        }
    }
}
