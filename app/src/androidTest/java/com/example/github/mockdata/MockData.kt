package com.example.github.models

class MockData {
    companion object {
        fun githubUser1(): GithubUser {
            return GithubUser(
                login = "testuser1",
                id = 1,
                node_id = "node1",
                avatar_url = "https://example.com/avatar1.png",
                url = "https://github.com/user1",
                repos_url = "https://api.github.com/users/user1/repos",
                name = "John Doe",
                company = "Example Inc.",
                location = "New York, USA",
                email = "john.doe@example.com",
                bio = "Software Developer",
                twitter_username = "johndoe123",
                followers = 500,
                created_at = "2023-07-20T12:34:56Z",
                updated_at = "2023-07-23T18:45:30Z"
            )
        }

        fun githubUser2(): GithubUser {
            return GithubUser(
                login = "testuser2",
                id = 2,
                node_id = "node2",
                avatar_url = "https://example.com/avatar2.png",
                url = "https://github.com/user2",
                repos_url = "https://api.github.com/users/user2/repos",
                name = "Jane Smith",
                company = "Acme Corp",
                location = "San Francisco, USA",
                email = "jane.smith@example.com",
                bio = "Frontend Developer",
                twitter_username = "janesmith456",
                followers = 1000,
                created_at = "2023-06-15T08:45:30Z",
                updated_at = "2023-07-21T14:20:00Z"
            )
        }

        fun repoListWith1Element(): List<GithubRepository>{
            return listOf(
                GithubRepository(
                    id = 1,
                    name = "repo1",
                    full_name = "user1/repo1",
                    description = "Mock repository 1",
                    is_fork = false,
                    created_at = "2023-07-22T12:34:56Z",
                    updated_at = "2023-07-22T18:45:30Z",
                    stargazers_count = 10,
                    watchers_count = 5,
                    forks_count = 3,
                    archived = false,
                    license = null,
                    forks = 0,
                    owner = Owner(githubUser1().name)
                )
            )
        }

        fun repoListWith2Element(): List<GithubRepository> {
            return listOf(
                GithubRepository(
                    id = 1,
                    name = "repo1",
                    full_name = "user1/repo1",
                    description = "Mock repository 1",
                    is_fork = false,
                    created_at = "2023-07-22T12:34:56Z",
                    updated_at = "2023-07-22T18:45:30Z",
                    stargazers_count = 10,
                    watchers_count = 5,
                    forks_count = 3,
                    archived = false,
                    license = null,
                    forks = 0,
                    owner = Owner(githubUser1().name)
                ),
                GithubRepository(
                    id = 2,
                    name = "repo2",
                    full_name = "user1/repo2",
                    description = "Mock repository 2",
                    is_fork = true,
                    created_at = "2023-07-23T10:12:34Z",
                    updated_at = "2023-07-23T15:30:45Z",
                    stargazers_count = 20,
                    watchers_count = 8,
                    forks_count = 5,
                    archived = false,
                    license = null,
                    forks = 1,
                    owner = Owner(githubUser1().name)
                )
            )
        }

    }
}
