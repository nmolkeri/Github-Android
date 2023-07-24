package com.example.github.ui.screens

import AvatarImage
import ForksCountView
import IconTextSection
import SharedViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.github.ui.theme.ColorPalette
import com.example.github.ui.theme.ImageResource

@Composable
fun RepoDetails(viewModel: SharedViewModel) {
    val selectedRepo = viewModel.selectedRepo.collectAsState()
    val user = viewModel.user.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = ColorPalette.BlackDark
            )
    ) {
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
                if (user != null) {
                    Column {
                        Text(
                            text = "${user.login}/", style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = ColorPalette.SecondaryTextColor
                            )
                        )
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
            ForksCountView(viewModel.forkCount.value)
            IconTextSection(
                icon = painterResource(id = ImageResource.FORK.resId),
                text = "${selectedRepo.value?.forks}"
            )
            IconTextSection(
                icon = painterResource(id = ImageResource.ARCHIVE.resId),
                text = "${selectedRepo.value?.archived}"
            )
            IconTextSection(
                icon = painterResource(id = ImageResource.STAR.resId),
                text = "${selectedRepo.value?.stargazers_count}"
            )
            IconTextSection(
                icon = painterResource(id = ImageResource.WATCH.resId),
                text = "${selectedRepo.value?.watchers_count}"
            )
            if (selectedRepo.value?.license?.name != null) {
                IconTextSection(
                    icon = painterResource(id = ImageResource.LICENSE.resId),
                    text = "${selectedRepo.value?.license?.name}"
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Description", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = ColorPalette.PrimaryTextColor
                )
            )
            Text(
                text = selectedRepo.value?.description ?: "N/A", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = ColorPalette.PrimaryTextColor
                )
            )

            Text(
                text = "Created at ${selectedRepo.value?.getCreatedDateAsString()}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = ColorPalette.SecondaryTextColor,
                ),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 80.dp)
            )
        }
    }
}