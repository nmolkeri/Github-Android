import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.github.ui.theme.ColorPalette
import com.example.github.ui.theme.ImageResource

@Composable
fun IconTextSection(
    icon: Painter,
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Box(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = icon,
                contentDescription = "My Icon",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(4.dp)),
                colorFilter = ColorFilter.tint(color = Color.White),
            )
            Text(
                text = text,
                color = ColorPalette.PrimaryTextColor,
                style = TextStyle(fontSize = 20.sp, fontWeight = fontWeight),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun IconTextSectionDemo() {
    Surface {
        IconTextSection(
            icon = painterResource(id = ImageResource.ARCHIVE.resId),
            text = "Full name of repo: Hellogggg",
            modifier = Modifier.padding(16.dp)
        )
    }
}
