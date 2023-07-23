package com.example.github.api

import android.util.Log
import com.example.github.models.GithubRepository
import com.example.github.models.GithubUser
import com.example.githubui.api.GithubAPIEndpoints
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
    class GithubApiClient(private val retrofit: GithubAPIEndpoints): IGithubAPIClient {
//        var retrofit =  Retrofit().gitHubApiService

        override suspend fun getRepoList(name: String): ResponseDataGithubRepos {
            return try {
                val response =  retrofit.getUserRepositories(name)
                if (response.isNotEmpty()) {
                    ResponseDataGithubRepos(Status.SUCCESS, response, "")
                } else {
                    Log.e("API_ERROR", "Error occurred")
                    ResponseDataGithubRepos(Status.ERROR, emptyList(), "")
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
