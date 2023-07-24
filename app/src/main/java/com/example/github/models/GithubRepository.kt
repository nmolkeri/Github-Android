package com.example.github.models

import android.annotation.SuppressLint
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class GithubRepository(
    val id: Long,
    val name: String,
    val full_name: String,
    val description: String?,
    val is_fork: Boolean,
    val created_at: String,
    val updated_at: String,
    val stargazers_count: Int,
    val watchers_count: Int,
    val forks_count: Int,
    val archived: Boolean,
    val license: GitHubLicense?,
    val forks: Int,
    val owner: Owner
){
    @SuppressLint("SimpleDateFormat")
    fun getCreatedDateAsString(): String {
        val instant = Instant.parse(created_at)
        val outputFormat = "yyyy-MM-dd HH:mm:ss"
        val formatter = DateTimeFormatter.ofPattern(outputFormat).withZone(ZoneId.systemDefault())
        return formatter.format(instant)
    }
    @SuppressLint("SimpleDateFormat")
    fun getUpdatedDateAsString(): String {
        val instant = Instant.parse(updated_at)
        val outputFormat = "yyyy-MM-dd HH:mm:ss"
        val formatter = DateTimeFormatter.ofPattern(outputFormat).withZone(ZoneId.systemDefault())
        return formatter.format(instant)
    }
}

data class GitHubLicense(
    val key: String,
    val name: String,
)

data class Owner(
    val login: String
)
