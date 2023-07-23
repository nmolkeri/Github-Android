import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.size.Scale
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AvatarImage(avatarUrl: String) {
    Box(
        modifier = Modifier.size(60.dp).clip(CircleShape)
    ) {
        Image(
            painter = rememberImagePainter(
                data = avatarUrl,
                builder = {
                    scale(Scale.FILL)
                }
            ),
            contentDescription = "Avatar Image",
            modifier = Modifier.fillMaxSize().graphicsLayer {
                shape = CircleShape
            },
            contentScale = ContentScale.Crop
        )
    }
}
