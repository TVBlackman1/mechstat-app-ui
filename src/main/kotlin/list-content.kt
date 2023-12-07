import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import internal.*
import repository.experiments.ListResponse
import repository.repository

@Composable
fun ListContent(modifier: Modifier) {
    var page by remember { mutableStateOf(1) }
    var experiments by remember {
        mutableStateOf<ArrayList<ListResponse>>(ArrayList())
    }
    LaunchedEffect(page) {
        experiments = repository.experiments.getExperiments(page, 16)
    }
    val columnWidth = 640

    Row (modifier = modifier) {
        Section(Width(columnWidth)) {
            SubSection(Height(740)) {
                Column {
                    ListComponent(experiments, Modifier.weight(1f))
                    Row(
                        modifier = Modifier.width(columnWidth.dp),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        IconButton(
                            content = {Icon(Icons.Default.KeyboardArrowLeft, "left")},
                            onClick = {
                                page--
                            },
                            enabled = page != 1
                        )
                        IconButton(
                            content = {Icon(Icons.Default.KeyboardArrowRight, "right")},
                            onClick = {
                                page++
                            },
                        )
                    }
                }
            }
            SubSection(Height(100)) {
                Text("Other info")
            }
        }
        Section(MaxWidth()) {
            SubSection(Height(100)) {
                Text("content")
            }
        }
    }
}