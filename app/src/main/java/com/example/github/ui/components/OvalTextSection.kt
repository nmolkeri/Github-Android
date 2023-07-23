import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OvalTextSection(
    text: String,
    modifier: Modifier = Modifier,
    typography: Typography = MaterialTheme.typography,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Box(
        modifier = modifier
            .graphicsLayer(alpha = 0.6f) // Apply 60% opacity to the background
            .background(Color.White, shape = CircleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = Color.Black,
            style = typography.bodyMedium.copy(fontSize = 16.sp, fontWeight = fontWeight),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Preview
@Composable
fun OvalTextSectionDemo() {
    Surface {
        // Replace the text and color values with your desired values
        OvalTextSection(
            text = "Full name of repo: Hello",
            modifier = Modifier
                .padding(16.dp)
        )
    }
}
