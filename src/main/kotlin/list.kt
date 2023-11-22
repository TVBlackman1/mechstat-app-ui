import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max

@Composable
fun ListComponent(data: ArrayList<ListResponse>) {
    Column {
        ListRow(
            number = "номер",
            material = "материал",
            experimentType = "тип",
            responsible = "ответственный",
        )
        LazyColumn {
            items(data) {
                ListRow(
                    number = it.number.toString(),
                    material = it.material,
                    experimentType = it.experimentType,
                    responsible = it.responsible,
                )
            }
        }
    }
}


@Preview
@Composable
fun ListComponentPreview() {
    val data = arrayListOf(
        ListResponse(
            number = 12,
            material = "Ст_20_320",
            responsible = "Анатольев А. Н.",
            experimentType = "растяжение"
        ),
        ListResponse(
            number = 12,
            material = "Ст_20_320",
            responsible = "Сидоров И. Б.",
            experimentType = "сжатие"
        ),
    )
    ListComponent(data)
}

fun listModifier(): Modifier {
    val summaryWidth = ListElementSizes.summaryWidth()
    val defaultWidth = 350.dp
    val width = max(defaultWidth, summaryWidth)
    return Modifier.width(width)
}

@Composable
fun ListRow(
    number: String,
    material: String,
    experimentType: String,
    responsible: String
) {
    val shape = RoundedCornerShape(4.dp)
    Row (
        modifier = listModifier()
            .padding(4.dp).background(
                color = Color(0xFFF1F8F8),
                shape = shape
            )
            .height(30.dp)
            .padding(5.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ){
        NumberListElement(number)
        MaterialListElement(material)
        ExperimentTypeListElement(experimentType)
        ResponsibleListElement(responsible)
    }
}