package com.example.githubui.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    private val BASE_URL = "https://api.github.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val gitHubApiService: GithubAPIEndpoints by lazy {
        retrofit.create(GithubAPIEndpoints::class.java)
    }
}
