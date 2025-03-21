package com.example.mynewsapplication.core

import androidx.compose.animation.EnterTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mynewsapplication.core.theme.MyNewsApplicationTheme
import com.example.mynewsapplication.news.navigation.Routes
import com.example.mynewsapplication.news.ui.newsList.newsListNavigation

@Composable
fun MyNewsApp() {
    MyNewsApplicationTheme {
        val navController = rememberNavController()

        NavHost(
            navController, startDestination = Routes.NewsList.route,
            enterTransition = { EnterTransition.None },
        ) {
            newsListNavigation(navController)
        }
    }
}
