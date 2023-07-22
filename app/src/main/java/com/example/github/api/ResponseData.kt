package com.example.github.api

import com.example.github.models.GithubRepository
import com.example.github.models.GithubUser

enum class Status {
    SUCCESS,
    ERROR
}
data class ResponseDataGithubUser(val status: Status, val data: GithubUser?, val errorMessage: String)
data class ResponseDataGithubRepos(val status: Status, val data:  List<GithubRepository>, val errorMessage: String)

