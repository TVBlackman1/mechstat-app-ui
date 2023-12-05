import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Content(type: ContentType, modifier: Modifier) {
    when (type) {
        ContentType.NEW -> Text("new")
        ContentType.LIST -> ListContent(modifier)
        ContentType.SETTINGS -> Text("settings")
    }
}

enum class ContentType {
    NEW,
    LIST,
    SETTINGS
}