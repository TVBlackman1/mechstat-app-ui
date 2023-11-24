import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun ListContent() {
    val density = LocalDensity.current
    var columnWitdh by remember {
        mutableStateOf(0.dp)
    }
    var page by remember { mutableStateOf(1) }
    var experiments by remember {
        mutableStateOf<ArrayList<ListResponse>>(ArrayList())
    }
    LaunchedEffect(page) {
        experiments = getData(page)
    }
    Column(
        modifier = Modifier.onSizeChanged {size ->
            columnWitdh = density.run {size.width.toDp() }
        },
    ) {
        ListComponent(experiments)
        Row(
            modifier = Modifier.width(columnWitdh),
            horizontalArrangement = Arrangement.End,
        ) {
            IconButton(
                content = {Icon(Icons.Default.KeyboardArrowLeft, "left")},
                onClick = {
                    if (page > 1) {
                        page--
                    }
                },
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