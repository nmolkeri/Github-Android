package com.example.github.ui.screens

import AvatarImage
import RepositoryList
import SharedViewModel
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.github.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchView(navController: NavController, viewModel: SharedViewModel) {
    val ctx = LocalContext.current
    var user = viewModel.user.value
    var name = viewModel.name.collectAsState()
    var repos = viewModel.repoList.value

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    // Hide the keyboard when tapping anywhere else on the screen
                    keyboardController?.hide()
                }
            }
    ) {
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

        Button(
            onClick = {
                if(name.value == "") {
                    Toast.makeText(ctx, "Enter name", Toast.LENGTH_SHORT).show()
                } else {
                    keyboardController?.hide()
                    viewModel.getUserDetails()
                }
                 },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Fetch user details")
        }

        if (user != null) {
            Row(modifier = Modifier.padding(start = 16.dp)) {
                AvatarImage(avatarUrl = user.avatar_url)
                Text(
                    text = user.name ?: "N/A",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        val onItemClick: (Long) -> Unit = { id ->
            viewModel.setSelectedId(id)
            navController.navigate(Screen.Details.route)
        }

        if(repos.isNotEmpty()) {
            RepositoryList(repositoryList = repos, onItemClick)
        }
    }

}