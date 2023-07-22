package com.example.github.ui.screens

import SharedViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun RepoDetails(navController: NavController, viewModel: SharedViewModel) {
    var selectedID = viewModel.selectedId.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
//        Text(
//            modifier = Modifier.clickable {
//                navController.popBackStack()
//            },
//            text = "This is Details view",
//            color = Color.Blue,
//            fontWeight = FontWeight.Bold

//        )
        Text(
//            modifier = Modifier.clickable {
//                navController.popBackStack()
//            },
            text = "Selected id is ${selectedID.value}",
            color = Color.Blue,
            fontWeight = FontWeight.Bold

        )
    }
}