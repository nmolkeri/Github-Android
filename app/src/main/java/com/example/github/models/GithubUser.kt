package com.example.github.models

data class GithubUser(
    val login: String,
    val id: Long,
    val node_id: String,
    val avatar_url: String,
    val url: String,
    val repos_url: String,
    val name: String,
    val company: String,
    val location: String,
    val email: String?,
    val bio: String?,
    val twitter_username: String?,
    val followers: Int,
    val created_at: String,
    val updated_at: String
)