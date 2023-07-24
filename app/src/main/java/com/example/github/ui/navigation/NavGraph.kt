package com.example.github.ui.navigation

import SharedViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.github.ui.screens.RepoDetails
import com.example.github.ui.screens.SearchView

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    val viewModel = viewModel<SharedViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.Search.route
    ) {
        composable(
            route = Screen.Search.route
        ) {
            SearchView(navController, viewModel)
        }
        composable(
            route = Screen.Details.route
        ) {
            RepoDetails(viewModel)
        }
    }
}