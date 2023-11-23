import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun ListContent() {
    var page by remember { mutableStateOf(1) }
    var experiments by remember {
        mutableStateOf<ArrayList<ListResponse>>(ArrayList())
    }
    LaunchedEffect(page) {
        experiments = getData(page)
    }
    Column {
        ListComponent(experiments)
        Row {
            Button(onClick = {
                if (page > 1) {
                    page--
                }
            }) {
                Text("prev")
            }
            Button(onClick = {
                page++
            }) {
                Text("next")
            }
        }
    }
}