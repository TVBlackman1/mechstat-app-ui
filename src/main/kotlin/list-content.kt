import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
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
    val summaryWidth = ListElementSizes.summaryWidth()
    val defaultWidth = 640.dp
    val columnWidth = max(defaultWidth, summaryWidth)
    val shape = RoundedCornerShape(0.dp)

    Row (modifier = modifier){
        Column(
            modifier = Modifier
                .width(columnWidth)
                .height(740.dp)
                .background(Color.White, shape)
                .padding(top = 4.dp, start = 6.dp, bottom = 4.dp, end = 6.dp),
        ) {
            ListComponent(experiments, Modifier.weight(1f))
            Row(
                modifier = Modifier.width(columnWidth),
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

        Column (modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .padding(start = 3.dp)
            .background(Color.White, shape)
        ) {
            Text("content")
        }
    }
}