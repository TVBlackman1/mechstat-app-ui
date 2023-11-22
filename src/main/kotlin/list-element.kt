import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ListElement(width: Dp, body: String) {
    Text(
        modifier = Modifier.width(width),
        textAlign = TextAlign.Start,
        text = body
    )
}

val symbolSize = 10.dp
@Composable
fun NumberListElement(body: String) = ListElement(width = symbolSize*5, body = body)

@Composable
fun MaterialListElement(body: String) = ListElement(width = symbolSize*11, body = body)

@Composable
fun ExperimentTypeListElement(body: String) = ListElement(width = symbolSize*13, body = body)

@Composable
fun ResponsibleListElement(body: String) = ListElement(width = symbolSize*32, body = body)