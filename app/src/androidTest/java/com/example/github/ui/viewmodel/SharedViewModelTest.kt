import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewModelScope
import com.example.github.api.GithubApiClient
import com.example.github.api.ResponseDataGithubRepos
import com.example.github.api.ResponseDataGithubUser
import com.example.github.api.Status
import com.example.github.models.GithubRepository
import com.example.github.models.GithubUser
import com.example.github.models.MockData
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class SharedViewModelTest {

    // Use a TestCoroutineDispatcher for testing suspending functions
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: SharedViewModel

    @Before
    fun setup() {
        viewModel = SharedViewModel()
    }

    @Test
    fun testGetUserDetails_Success() = runBlockingTest {
        // Mock GithubApiClient
        val mockApiClient = Mockito.mock(GithubApiClient::class.java)
        viewModel.retrofit.gitHubApiService = mockApiClient

        // Mock GithubUser and GithubRepository response
        val mockedUser = MockData.githubUser1()
        val mockedRepoList = MockData.repoListWith2Element()
        `when`(mockApiClient.getUserInfo("testUser")).thenReturn(
            ResponseDataGithubUser(Status.SUCCESS, mockedUser, "")
        )
        `when`(mockApiClient.getRepoList("testUser")).thenReturn(
            ResponseDataGithubRepos(Status.SUCCESS, mockedRepoList, "")
        )

        // Set the name and call getUserDetails
        viewModel.setName("testUser")
        viewModel.getUserDetails()

        // Assert the state values after API call
        assertEquals(mockedUser, viewModel.user.value)
        assertEquals(mockedRepoList, viewModel.repoList.value)
        assertEquals(mockedRepoList.sumBy { it.forks_count }, viewModel.forkCount.value)
    }

    @Test
    fun testGetUserDetails_Failure() = runBlockingTest {
        // Mock GithubApiClient
        val mockApiClient = Mockito.mock(GithubApiClient::class.java)
        viewModel.retrofit.gitHubApiService = mockApiClient

        // Mock API failure response for both getUserInfo and getRepoList
        `when`(mockApiClient.getUserInfo("testUser")).thenReturn(
            ResponseDataGithubUser(Status.ERROR, null, "")
        )
        `when`(mockApiClient.getRepoList("testUser")).thenReturn(
            ResponseDataGithubRepos(Status.ERROR, emptyList(), "")
        )

        // Set the name and call getUserDetails
        viewModel.setName("testUser")
        viewModel.getUserDetails()

        // Assert the state values after API call
        assertEquals(null, viewModel.user.value)
        assertEquals(emptyList<GithubRepository>(), viewModel.repoList.value)
        assertEquals(0, viewModel.forkCount.value)
    }

    // ... Other test cases ...
}
