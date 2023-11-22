import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    val buttons = remember {getButtons().toMutableList()}
//    val coroutineScope = rememberCoroutineScope()
//    coroutineScope.launch(Dispatchers.IO) {
//        getData()
//    }

    val experiments = produceState(
        initialValue = ArrayList<ListResponse>(),
        producer = {
            value = getData()
        }
    )
//    LaunchedEffect(key1 = ApiRoutes.EXPERIMENTS) {
//         getData()
//    }
    Scaffold {
        MaterialTheme {
            Column {
//            Button(onClick = { buttons.add(Button("asdf"))}) {
//                Text("add")
//            }
//                LeftSide(buttons)
                ListComponent(experiments.value)
//                NavigationRailSample(buttons)
            }
        }
        // after add it fo left side NavigationRail {  }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
//        TestScrollableContentWithButton()
    }
}
