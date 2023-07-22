package com.example.github.api

import android.util.Log
import com.example.github.models.GithubRepository
import com.example.github.models.GithubUser
import com.example.githubui.api.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//import ir.sdrv.mobilletsample.data.remote.api.GithubApi
//import ir.sdrv.mobilletsample.data.remote.api.base.Resource
//import ir.sdrv.mobilletsample.data.remote.api.models.*
//import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface  IGithubAPIClient {
    suspend fun getRepoList(name: String) : ResponseDataGithubRepos
    suspend fun getUserInfo(name: String) : ResponseDataGithubUser
}
    class GithubApiClient(): IGithubAPIClient {
        var retrofit =  Retrofit.gitHubApiService

        override suspend fun getRepoList(name: String): ResponseDataGithubRepos {
            try {
                val response = Retrofit.gitHubApiService.getUserRepositories(name)
                if (response.isNotEmpty()) {
                    println("SUcessssss ------------------------------------> $response")
                    return ResponseDataGithubRepos(Status.SUCCESS, response, "")
                } else {
                    return ResponseDataGithubRepos(Status.ERROR, emptyList(), "")
                    Log.e("API_ERROR", "Error occured")
                }
            } catch (ex: Throwable) {
                Log.e("NETWORK_ERROR", ex.message ?: "Unknown error occurred")
                return ResponseDataGithubRepos(Status.ERROR, emptyList(), "")
            }
        }

        override suspend fun getUserInfo(name: String): ResponseDataGithubUser {
            try {
                val response = Retrofit.gitHubApiService.getUserDetails(name)
                if (response != null) {
                    println("SUcessssss ------------------------------------> $response")
                    return ResponseDataGithubUser(Status.SUCCESS, response, "")
                } else {
                    return ResponseDataGithubUser(Status.ERROR, null, "")
                    Log.e("API_ERROR", "Error")
                }
            } catch (ex: Throwable) {
                Log.e("NETWORK_ERROR", ex.message ?: "Unknown error occurred")
                return ResponseDataGithubUser(Status.ERROR, null, "")
            }
        }
    }
