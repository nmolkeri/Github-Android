import com.example.github.api.GithubApiClient
import com.example.github.api.MockGithubAPIEndpoints
import com.example.github.models.GithubRepository
import com.example.github.models.MockData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GithubApiClientTest {

    private val githubApiClient = GithubApiClient(MockGithubAPIEndpoints())

    @Test
    fun test_getRepoList_API_endpoint() = runBlocking {
        // Call the API endpoint
        val mockUsername = "testuser"
        val resultRepos = githubApiClient.getRepoList(mockUsername)

        // Verify the result
        val expectedRepos = MockData.repoListWith2Element()
        assertEquals(expectedRepos, resultRepos.data)
    }

    @Test
    fun test_getUserInfo_API_endpoint() = runBlocking {
        // Call the API endpoint
        val mockUsername = "testuser"
        val resultUser = githubApiClient.getUserInfo(mockUsername)

        // Verify the result
        val expectedUser = MockData.githubUser1()
        assertEquals(expectedUser, resultUser.data)
    }

    @Test
    fun test_getRepoList_API_endpoint_invalid_username() = runBlocking {
        // Call the API endpoint
        val mockUsername = "invalid_username"
        val resultRepos = githubApiClient.getRepoList(mockUsername)

        // Verify the result
        val expectedRepos = emptyList<GithubRepository>()
        assertEquals(expectedRepos, resultRepos.data)
    }
}
