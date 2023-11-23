import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Content(type: ContentType) {
    when (type) {
        ContentType.NEW -> Text("new")
        ContentType.LIST -> ListContent()
        ContentType.SETTINGS -> Text("settings")
    }
}

enum class ContentType {
    NEW,
    LIST,
    SETTINGS
}