import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector

data class Button(
    val text: String,
    val icon: ImageVector,
    val desc: String
)

typealias onContentType = (type: ContentType) -> Unit
@Composable
fun LeftSide(buttons: Array<Button>, contentType: ContentType,
             pick: onContentType) {
        NavigationRail {
            buttons.forEachIndexed { index, item ->
                NavigationRailItem(
                    icon = { Icon(item.icon, item.desc) },
                    label = { Text(item.text) },
                    selected = contentType.ordinal == index,
                    onClick = {
                        val selected = ContentType.values()[index]
                        pick(selected)
                    }
                )
            }
    }
}

fun getButtons(): Array<Button> {
    val _buttons = arrayOf(
        Button(
            text = "new",
            icon = Icons.Default.PlayArrow,
            desc= "Start new"
        ),
        Button(
            text = "list",
            icon = Icons.Default.List,
            desc= "Experiments"
        ),
        Button(
            text = "settings",
            icon = Icons.Default.Settings,
            desc= "Debug settings"
        ),
    )
    return _buttons
}
