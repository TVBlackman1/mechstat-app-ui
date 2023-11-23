import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListElement(width: Dp, body: String, style: ListRowStyle) {
    Text(
        modifier = Modifier.width(width),
        textAlign = TextAlign.Start,
        text = body,
        style = styleText(style)
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
fun NumberListElement(body: String, style: ListRowStyle) = ListElement(
    width = ListElementSizes.Number, body = body, style = style
)

@Composable
fun MaterialListElement(body: String, style: ListRowStyle) = ListElement(
    width = ListElementSizes.Material, body = body, style = style
)

@Composable
fun ExperimentTypeListElement(body: String, style: ListRowStyle) = ListElement(
    width = ListElementSizes.ExperimentType, body = body, style = style
)

@Composable
fun ResponsibleListElement(body: String, style: ListRowStyle) = ListElement(
    width = ListElementSizes.Responsible, body = body, style = style
)

fun styleText(style: ListRowStyle): TextStyle {
    return when(style) {
        ListRowStyle.HEADER -> TextStyle(
            color = Color(0xFF2C3E50),
            fontFamily = interFont400,
            fontSize = 14.sp,
        )
        ListRowStyle.BODY -> TextStyle(
            color = Color(0xFF2C3E50),
            fontFamily = interFont400,
            fontSize = 15.sp,
        )
    }
}