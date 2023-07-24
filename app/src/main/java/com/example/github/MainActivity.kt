package com.example.github

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.github.ui.navigation.SetupNavGraph
import com.example.github.ui.theme.GithubTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
        }
    }
}
