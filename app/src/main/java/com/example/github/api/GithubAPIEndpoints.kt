package com.example.githubui.api
import com.example.github.models.GithubRepository
import com.example.github.models.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPIEndpoints {

    @GET("users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): GithubUser

    @GET("users/{username}/repos")
    suspend fun getUserRepositories(@Path("username") username: String): List<GithubRepository>
}
