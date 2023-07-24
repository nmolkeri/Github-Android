import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ForksCountView(totalForks: Int) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val starColor = if (totalForks > 5000) {
            Color(0xFFFFD700)
        } else {
            Color.Red
        }
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Created at",
            tint = starColor,
            modifier = Modifier.size(32.dp)
        )

        Text(
            text = totalForks.toString(),
            color = if (totalForks > 5000) Color(0xFFFFD700) else Color.Red,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
