import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListComponent(data: ArrayList<ListResponse>) {
    val shape = RoundedCornerShape(4.dp)
    Column {
        Row (
            modifier = Modifier
                .width(400.dp)
                .padding(4.dp).border(0.dp, Color.Black, shape).background(
                    color = Color(0xFFF1F8F8),
                    shape = shape
                )
                .height(30.dp)
                .padding(5.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ){
            NumberListElement("номер")
            MaterialListElement("материал")
            ExperimentTypeListElement("тип")
            ResponsibleListElement("ответственный")
        }
        LazyColumn {
            items(data) {

                Row (modifier = Modifier
                    .width(400.dp)
                    .padding(4.dp).border(0.dp, Color.Black, shape).background(
                        color = Color(0xFFF1F8F8),
                        shape = shape
                    )
                    .height(30.dp)
                    .padding(5.dp),

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    NumberListElement(it.number.toString())
                    MaterialListElement(it.material)
                    ExperimentTypeListElement(it.experimentType)
                    ResponsibleListElement(it.responsible)
                }
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