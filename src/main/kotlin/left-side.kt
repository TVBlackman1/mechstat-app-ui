import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

data class Button(
    val text: String,
    val icon: ImageVector,
    val desc: String,
    val contentType: ContentType
)

data class LeftSideContent(
    val top: Array<Button>,
    val bottom: Array<Button>,
)

typealias onContentType = (type: ContentType) -> Unit

@Composable
fun LeftSide(content: LeftSideContent, contentType: ContentType,
             pick: onContentType) {
    val displayButton: @Composable (Button)->Unit = {
        NavigationRailItem(
            icon = { Icon(it.icon, it.desc) },
            label = { Text(it.text) },
            selected = it.contentType == contentType,
            onClick = {
                pick(it.contentType)
            }
        )
    }
    NavigationRail {
        for (button in content.top) {
            displayButton(button)
        }
        Spacer(modifier = Modifier.weight(1.0f))
        for (button in content.bottom) {
            displayButton(button)
        }
    }
}

fun getLeftSideContent(): LeftSideContent {
    return LeftSideContent(
        top = arrayOf(
            Button(
                text = "new",
                icon = Icons.Default.PlayArrow,
                desc= "Start new",
                contentType = ContentType.NEW,
            ),
            Button(
                text = "list",
                icon = Icons.Default.List,
                desc= "Experiments",
                contentType = ContentType.LIST,
            ),
        ),
        bottom = arrayOf(
            Button(
                text = "settings",
                icon = Icons.Default.Settings,
                desc= "Debug settings",
                contentType = ContentType.SETTINGS,
            ),
        ),
    )
}
