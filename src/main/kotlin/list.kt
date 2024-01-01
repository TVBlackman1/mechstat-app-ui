import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import repository.experiments.dto.ListItem

@Composable
fun ListComponent(data: ArrayList<ListItem>?, columnModifier: Modifier,
                  onChangeData: (dataId: Int) -> Unit
                  ) {
//    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {

        var selectedIndex by remember { mutableStateOf(-1) }
        val content: @Composable () -> Unit = {
            if (data!!.size == 0) {
                Row {
                    Icon(
                        Icons.Default.Warning, "no data",
                        tint = Color(0xFFDCDCDC),
                        modifier = Modifier.size(size = 64.dp),
                    )
                }
            } else {
                LazyColumn {
                    itemsIndexed(data) { index, it ->
                        ListRow(
                            number = it.number.toString(),
                            material = it.material,
                            experimentType = it.experimentType,
                            responsible = it.responsible,
                            isSelected = index == selectedIndex,
                            onClick = {
                                selectedIndex = index
                                onChangeData(it.number)
                            }
                        )
                    }
                }
            }
        }

        Column(modifier = columnModifier) {
            ListRow(
                number = "номер",
                material = "материал",
                experimentType = "тип",
                responsible = "ответственный",
                style = ListRowStyle.HEADER,
            )

            if (data != null) {
                when (data.size) {
                    0 -> Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) { content() }

                    else -> Column { content() }
                }
            }
        }
//    }
}

@Composable
fun ListRow(
    number: String,
    material: String,
    experimentType: String,
    responsible: String,
    style: ListRowStyle = ListRowStyle.BODY,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val shape = RoundedCornerShape(4.dp)
    var usingStyle: ListRowStyle = style
    if (isSelected) {
        usingStyle = ListRowStyle.SELECTED_BODY
    }
    val color = color(usingStyle)
    Row (
        modifier = Modifier.fillMaxWidth()
            .padding(usingStyle)
            .background(
                color = Color(color),
                shape = shape
            ).clickable(onClick = onClick)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,

    ){
        NumberListElement(number, style)
        MaterialListElement(material, style)
        ExperimentTypeListElement(experimentType, style)
        ResponsibleListElement(responsible, style)
    }
}

fun color(style: ListRowStyle): Long {
    return when(style) {
        ListRowStyle.HEADER -> 0xFFf4f4f7
        ListRowStyle.BODY -> 0xFFF1F8F8
        ListRowStyle.SELECTED_BODY -> 0xFFD2E9E9
    }
}

fun Modifier.padding(style: ListRowStyle): Modifier {
    return when(style) {
        ListRowStyle.HEADER -> this.height(32.dp).padding(2.dp)
        ListRowStyle.BODY, ListRowStyle.SELECTED_BODY-> this.height(36.dp).padding(3.dp)
    }
}

enum class ListRowStyle {
    HEADER,
    BODY,
    SELECTED_BODY,
}