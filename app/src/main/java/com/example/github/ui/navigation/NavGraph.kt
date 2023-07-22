package com.example.github.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.github.ui.screens.RepoDetails
import com.example.github.ui.screens.SearchView

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    val ctx = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.Search.route
    ){
        composable(
            route = Screen.Search.route
        ){
            SearchView(navController)
        }
        composable(
            route = Screen.Details.route
        ){ navBackStackEntry ->
            val repoId =  navBackStackEntry.arguments?.getString("repoId")
            println("--------------------------------------------------> $repoId")
            if (repoId == null) {
                Toast.makeText(ctx, "Repo Id is required", Toast.LENGTH_SHORT).show()
            } else {
                RepoDetails(navController, repoId)
            }
        }
    }
}