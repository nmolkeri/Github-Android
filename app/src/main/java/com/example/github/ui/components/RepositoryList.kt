import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.github.models.GithubRepository
import com.example.github.ui.theme.ColorPalette

@Composable
fun RepositoryList(
    repositoryList: List<GithubRepository>, onItemClick: (GithubRepository) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(repositoryList) { repository ->
            RepositoryItem(repository, onItemClick)
        }
    }
}

@Composable
fun RepositoryItem(repository: GithubRepository, onItemClick: (GithubRepository) -> Unit) {
    Column(modifier = Modifier.clickable {
        onItemClick(repository)
    }) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = repository.full_name, style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = ColorPalette.PrimaryTextColor
            ), color = ColorPalette.PrimaryTextColor
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}
