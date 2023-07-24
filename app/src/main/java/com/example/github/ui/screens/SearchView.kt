package com.example.github.ui.screens

import AvatarImage
import RepositoryList
import SharedViewModel
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.github.models.GithubRepository
import com.example.github.ui.navigation.Screen
import com.example.github.ui.theme.ColorPalette
import com.example.github.ui.theme.ImageResource

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchView(navController: NavController, viewModel: SharedViewModel) {
    val ctx = LocalContext.current
    var user = viewModel.user.value
    var name = viewModel.name.collectAsState()
    var repos = viewModel.repoList.value

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPalette.BlackDark)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(ColorPalette.BlackDark)
            .padding(16.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    keyboardController?.hide()
                }
            }) {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(value = name.value,
                    onValueChange = { newText ->
                        (viewModel::setName)(newText)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.7f)
                        .padding(16.dp),
                    label = { Text("Enter user here") })
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(0.3f)
                        .background(
                            ColorPalette.ButtonColor, shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            if (name.value.isEmpty()) {
                                Toast
                                    .makeText(ctx, "Enter name", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                keyboardController?.hide()
                                viewModel.getUserDetails()
                            }
                        }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Go",
                        fontSize = 18.sp,
                        color = ColorPalette.PrimaryTextColor,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            if (user != null) {
                Row(modifier = Modifier.padding(start = 16.dp)) {
                    AvatarImage(avatarUrl = user.avatar_url)
                    Column() {
                        Text(
                            text = user.name ?: "N/A", style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = ColorPalette.PrimaryTextColor
                            ), modifier = Modifier.padding(start = 8.dp)
                        )
                        Text(
                            text = user.company ?: "N/A", style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = ColorPalette.PrimaryTextColor
                            ), modifier = Modifier.padding(start = 8.dp)
                        )
                        Text(
                            text = user.location ?: "N/A", style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = ColorPalette.PrimaryTextColor
                            ), modifier = Modifier.padding(start = 8.dp)
                        )

                    }

                }

                val onItemClick: (GithubRepository) -> Unit = { id ->
                    viewModel.setSelectedRepo(id)
                    navController.navigate(Screen.Details.route)
                }

                if (repos.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Repositories", style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = ColorPalette.PrimaryTextColor
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(2.dp, Color.DarkGray, shape = RoundedCornerShape(2.dp))
                            .padding(8.dp)
                    ) {
                        RepositoryList(repositoryList = repos, onItemClick)
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = ImageResource.GITHUB_ICON.resId),
                        contentDescription = "My Icon",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        colorFilter = ColorFilter.tint(color = Color.White),
                    )
                    Text(
                        text = "Enter user name to see details", style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = ColorPalette.PrimaryTextColor
                        ), modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}