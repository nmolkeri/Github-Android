package com.example.github.ui.screens

import SearchViewModel
import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.github.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(navController: NavController) {
    val ctx = LocalContext.current
    val viewModel = viewModel<SearchViewModel>()
    var user = viewModel.user.value
    var name = viewModel.name.collectAsState()
//    var setName = viewModel.stringss::value
    println("----------------------------------------------------------------> $name")
    println("----------------------------------------------------------------> $user")

    Column {
//        TextField(
//            value = name,
//            onValueChange = viewModel::setName
//        )
        // Display the current value
        Text("Current Value: ${name.value}")
        TextField(
            value = name.value,
            onValueChange = { newText ->
                run { (viewModel::setName)(newText) }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = { Text("Enter user here") }
        )
//
//        TextField(
//            value = name,
//            onValueChange = { value ->
//                { (viewModel::setName)(value) }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            label = { Text("Enter your text here") }
//        )
        // Button to update the value
        Button(
            onClick = {
                if(name.value == "") {
                    Toast.makeText(ctx, "Enter name", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.getUserDetails()
                }
                 },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Fetch user details")
        }
    }

}