import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.github.models.GithubRepository

@Composable
fun RepositoryList(repositoryList: List<GithubRepository>, onItemClick: (GithubRepository) -> Unit) {
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
    Column(
        modifier = Modifier.clickable {
            onItemClick(repository)
        }
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = repository.full_name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = ColorPalette.PrimaryTextColor
            ),
            color = ColorPalette.PrimaryTextColor
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}
