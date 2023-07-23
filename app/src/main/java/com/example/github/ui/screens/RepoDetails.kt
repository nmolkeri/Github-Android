package com.example.github.ui.screens

import ForksCountView
import OvalTextSection
import SharedViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RepoDetails(navController: NavController, viewModel: SharedViewModel) {
    var selectedRepo = viewModel.selectedRepo.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ){
        ForksCountView(viewModel.forkCount.value)
        OvalTextSection(text = "Name: ${selectedRepo.value?.name}")
        OvalTextSection(text = "Description: ${selectedRepo.value?.description}")
        OvalTextSection(text = "Full name of repo: ${selectedRepo.value?.full_name}")
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