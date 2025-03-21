package com.example.mynewsapplication.news.navigation

sealed class Routes(val route: String) {
    data object NewsList : Routes("NEWS_LIST")
    data object NewsDetails : Routes("NEWS_DETAILS")
}