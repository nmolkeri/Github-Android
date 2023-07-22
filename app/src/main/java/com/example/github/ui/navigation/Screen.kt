package com.example.github.ui.navigation

sealed class Screen(val route: String) {
    object Search: Screen(route = "searchView")
    object Details: Screen(route = "repoDetails/{repoId}") {
//        fun createRoute(repoId: Long) = "repoDetails/$repoId"
    }
}
