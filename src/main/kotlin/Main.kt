import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState


@Composable
@Preview
fun App() {
    var contentType by remember { mutableStateOf(ContentType.LIST) }

    val pickContentType: onContentType = {
        _contentType -> contentType = _contentType
    }
    
    Scaffold() {
        MaterialTheme {
            Box(modifier = Modifier
                .background(Color(0xFFF5F1EC))
                .fillMaxSize()
            ) {
                Row {
                    LeftSide(getLeftSideContent(), contentType, pickContentType)
                    Content(contentType)
                }
            }
        }
    }
}

fun main() = application {
    val windowState = rememberWindowState(size = DpSize.Unspecified)
    Window(onCloseRequest = ::exitApplication, windowState) {
        App()
    }
}
