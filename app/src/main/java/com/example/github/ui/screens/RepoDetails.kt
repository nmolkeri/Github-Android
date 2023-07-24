package com.example.github.ui.screens

import AvatarImage
import ColorPalette
import ForksCountView
import OvalTextSection
import SharedViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.github.R

@Composable
fun RepoDetails(navController: NavController, viewModel: SharedViewModel) {
    var selectedRepo = viewModel.selectedRepo.collectAsState()
    var user = viewModel.user.value
    val pullRequestIcon: Painter = painterResource(R.drawable.pull_request)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = ColorPalette.BlackDark
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                if(user != null)
                {
                    Column(

                    ) {
                        Text(text = "${user.login}/",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = ColorPalette.SecondaryTextColor
                            ))
                        Text(
                            text = "${selectedRepo.value?.name}",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = ColorPalette.PrimaryTextColor
                            ),
                        )
                    }
                    AvatarImage(avatarUrl = user.avatar_url)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Divider(
                color = Color.White,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(2.dp)
            )
            Image(
                painter = pullRequestIcon,
                contentDescription = "My Icon",
                modifier = Modifier.size(20.dp).clip(RoundedCornerShape(4.dp)),
                colorFilter = ColorFilter.tint(color = Color.White)
            )



            ForksCountView(viewModel.forkCount.value)
            OvalTextSection(text = "Description: ${selectedRepo.value?.description}")
            OvalTextSection(text = "Created at ${selectedRepo.value?.getCreatedDateAsString()}")
            OvalTextSection(text = "Updated at ${selectedRepo.value?.getUpdatedDateAsString()}")
            OvalTextSection(text = "Forks: ${selectedRepo.value?.forks}")
            OvalTextSection(text = "Archived: ${selectedRepo.value?.archived}")
            OvalTextSection(text = "Stars: ${selectedRepo.value?.stargazers_count}")
            OvalTextSection(text = "Watchers: ${selectedRepo.value?.watchers_count}")
            if(selectedRepo.value?.license?.name != null)
            {
                OvalTextSection(text = "License: ${selectedRepo.value?.license?.name}")
            }
        }

    }
}