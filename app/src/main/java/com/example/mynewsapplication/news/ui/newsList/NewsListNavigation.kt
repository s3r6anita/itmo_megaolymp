package com.example.mynewsapplication.news.ui.newsList

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.mynewsapplication.news.navigation.Routes


fun NavGraphBuilder.newsListNavigation(navController : NavHostController) {
    composable(route = Routes.NewsList.route) {
        val viewModel = hiltViewModel<NewsListViewModel>()
        NewsListScreen(viewModel)
    }
}