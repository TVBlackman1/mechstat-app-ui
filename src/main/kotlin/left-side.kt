import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.ktor.client.*
import kotlinx.serialization.Serializable

data class Button(
    val text: String,
    val icon: ImageVector,
    val desc: String
)

@Composable
fun LeftSide(buttons: MutableList<Button>) {
    var selectedItem by remember { mutableStateOf(0) }
        NavigationRail {
            buttons.forEachIndexed { index, item ->
                NavigationRailItem(
                    icon = { Icon(item.icon, item.desc) },
                    label = { Text(item.text) },
                    selected = selectedItem == index,
                    onClick = {
                        println(index)
                        selectedItem = index
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
        Button(
            text = "settings",
            icon = Icons.Default.Settings,
            desc= "Debug settings"
        ),
        Button(
            text = "settings",
            icon = Icons.Default.Settings,
            desc= "Debug settings"
        ),
    )
    return _buttons
}
