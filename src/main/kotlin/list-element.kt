import androidx.compose.foundation.layout.*
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

object ListElementSizes {
    val Number = symbolSize*5
    val Material = symbolSize*11
    val ExperimentType = symbolSize*13
    val Responsible = symbolSize*25
    fun summaryWidth(): Dp {
        val paddingSize = 0.dp
        val elementsCount = 4
        return Number + Material + ExperimentType + Responsible + paddingSize * elementsCount
    }
}
@Composable
fun NumberListElement(body: String) = ListElement(width = ListElementSizes.Number, body = body)

@Composable
fun MaterialListElement(body: String) = ListElement(width = ListElementSizes.Material, body = body)

@Composable
fun ExperimentTypeListElement(body: String) = ListElement(width = ListElementSizes.ExperimentType, body = body)

@Composable
fun ResponsibleListElement(body: String) = ListElement(width = ListElementSizes.Responsible, body = body)