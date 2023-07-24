import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.api.GithubApiClient
import com.example.github.api.Status
import com.example.github.models.GithubRepository
import com.example.github.models.GithubUser
import com.example.githubui.api.Retrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel() : ViewModel() {
    private val retrofit = Retrofit().gitHubApiService

    val user = mutableStateOf<GithubUser?>(null)
    val repoList = mutableStateOf<List<GithubRepository>>(emptyList())
    val loading = mutableStateOf(false)

    private val _selectedRepo = MutableStateFlow<GithubRepository?>(null)
    var selectedRepo = _selectedRepo.asStateFlow()

    private val _name = MutableStateFlow("")
    var name = _name.asStateFlow()

    val forkCount = mutableStateOf(0)
    fun setName(name: String) {
        _name.value = name
    }

    fun setSelectedRepo(repository: GithubRepository) {
        _selectedRepo.value = repository
    }

    private fun resetData() {
        user.value = null
        repoList.value = emptyList()
        _selectedRepo.value = null
        forkCount.value = 0
    }

    fun getUserDetails() {
        viewModelScope.launch {
            loading.value = true
            resetData()
            val response = GithubApiClient(retrofit).getUserInfo(name.value)
            if (response.status == Status.SUCCESS) {
                user.value = response.data
            }

            val reposResponse = GithubApiClient(retrofit).getRepoList(name.value)
            if (reposResponse.status == Status.SUCCESS) {
                repoList.value = reposResponse.data
                forkCount.value = getTotalForksCount()
            }

            loading.value = false
        }
    }

    private fun getTotalForksCount(): Int {
        var totalForksCount = 0
        for (repo in repoList.value) {
            totalForksCount += repo.forks_count
        }
        return totalForksCount
    }

}
