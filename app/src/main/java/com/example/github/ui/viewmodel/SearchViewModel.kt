import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.api.GithubApiClient
import com.example.github.api.Status
import com.example.github.models.GithubRepository
import com.example.github.models.GithubUser
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    val user = mutableStateOf<GithubUser?>(null)
    val repoList = mutableStateOf<List<GithubRepository>>(emptyList())
    val loading = mutableStateOf(false)

    private val _name = MutableStateFlow("")
    var name = _name.asStateFlow()

//    init {
//        getUserDetails("nmolkeri")
//    }

    fun setName(name: String) {
        println("Inside set name")
        _name.value = name
//        getUserDetails(name)
    }

    fun resetName() {
        println("Inside reset name")
        _name.value = ""
    }

    fun getUserDetails() {
        viewModelScope.launch {
            loading.value = true
//            Thread.sleep(3000)

            val response = GithubApiClient().getUserInfo(name.value)
            if (response.status == Status.SUCCESS) {
                println("-----------------------------> response is good ${response.data}")
                user.value = response.data
            }

//            val reposResponse = GithubApiClient().getRepoList(name)
//            if (reposResponse.status == Status.SUCCESS) {
//                repoList.value = reposResponse.data
//            }

            loading.value = false
        }
    }
}
