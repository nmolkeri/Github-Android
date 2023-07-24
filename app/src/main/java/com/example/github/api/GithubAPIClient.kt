package com.example.github.api

import android.util.Log
import com.example.githubui.api.GithubAPIEndpoints

interface IGithubAPIClient {
    suspend fun getRepoList(name: String): ResponseDataGithubRepos
    suspend fun getUserInfo(name: String): ResponseDataGithubUser
}

class GithubApiClient(private val retrofit: GithubAPIEndpoints) : IGithubAPIClient {
    override suspend fun getRepoList(name: String): ResponseDataGithubRepos {
        return try {
            val response = retrofit.getUserRepositories(name)
            if (response.isNotEmpty()) {
                ResponseDataGithubRepos(Status.SUCCESS, response, "")
            } else {
                Log.e("API_ERROR", "Error occurred")
                ResponseDataGithubRepos(Status.ERROR, emptyList(), "Error occurred")
            }
        } catch (ex: Throwable) {
            Log.e("NETWORK_ERROR", ex.message ?: "Unknown error occurred")
            ResponseDataGithubRepos(Status.ERROR, emptyList(), "")
        }
    }

    override suspend fun getUserInfo(name: String): ResponseDataGithubUser {
        return try {
            val response = retrofit.getUserDetails(name)
            if (response != null) {
                ResponseDataGithubUser(Status.SUCCESS, response, "")
            } else {
                Log.e("API_ERROR", "Error")
                ResponseDataGithubUser(Status.ERROR, null, "")
            }
        } catch (ex: Throwable) {
            Log.e("NETWORK_ERROR", ex.message ?: "Unknown error occurred")
            ResponseDataGithubUser(Status.ERROR, null, "")
        }
    }
}
