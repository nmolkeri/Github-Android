package com.example.github.api

import com.example.github.models.GithubRepository
import com.example.github.models.GithubUser
import com.example.github.models.MockData
import com.example.githubui.api.GithubAPIEndpoints

class MockGithubAPIEndpoints : GithubAPIEndpoints {

    override suspend fun getUserDetails(username: String): GithubUser {
        return MockData.githubUser1()
    }

    override suspend fun getUserRepositories(username: String): List<GithubRepository> {
        return if (username == MockData.githubUser1().login) {
            MockData.repoListWith2Element()
        } else {
            emptyList()
        }
    }
}
