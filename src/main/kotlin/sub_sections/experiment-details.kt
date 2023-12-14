package sub_sections

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import internal.MaxHeight
import internal.NoRippleTheme
import internal.SubSection
import montserratFont400
import montserratFont500
import montserratFont600
import java.io.File

@Composable
fun ExperimentDetailsSubSection() {
    SubSection(MaxHeight()) {
        Column {
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Text("5 марта 2022 17:52",
                    style = TextStyle(
                        color = Color(0xFF2C3E50),
                        fontFamily = montserratFont400,
                        fontSize = 18.sp,
                    ),
                    modifier = Modifier.padding(6.dp)
                )
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier
                    .width(984.dp)
                    .height(480.dp)
                    .background(Color(0xFFD2E9E9), RoundedCornerShape(6.dp))
                ) {
                    val file = File("C:\\Users\\vbifm\\IdeaProjects\\mechstat\\src\\main\\resources\\images\\chart-example.png")
                    val imageBitmap: ImageBitmap = remember(file) {
                        loadImageBitmap(file.inputStream())
                    }
                    Image(
                        painter = BitmapPainter(image = imageBitmap),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null
                    )
                }
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier
                    .width(984.dp)
                    .height(480.dp)
                    .padding(top = 6.dp)
                    .background(Color(0xFFF9F9F9), RoundedCornerShape(4.dp))
                ) {
                    var tabIndex by remember { mutableStateOf(0) }

                    val tabs = listOf("Основное", "Связанное", "Заказчик")

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row (
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                                TabRow(
                                    selectedTabIndex = tabIndex,
                                    backgroundColor  = Color.Transparent,
                                    modifier = Modifier.width(600.dp),
                                    indicator = {},
                                    divider = {},
                                ) {
                                    tabs.forEachIndexed { index, title ->
                                        Tab(text = { Text(
                                            title,
                                            style = TextStyle(
                                                color = if (tabIndex == index) Color(0xFF000000) else Color(0xFFA7A7A7),
                                                fontFamily = montserratFont600,
                                                fontSize = 14.sp,
                                            ),
                                        ) },
                                            modifier = Modifier
                                                .padding(start = 10.dp, end = 10.dp, top = 6.dp, bottom = 6.dp)
                                                .background(Color.Transparent),
                                            selected = tabIndex == index,
                                            onClick = { tabIndex = index },
                                        )
                                    }
                                }
                            }
                        }
                        when (tabIndex) {
                            0 -> DetailsInfo()
                            1 -> Text("Связанные эксперименты")
                            2 -> Text("Информация о заказчике")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailsInfo() {
    val data = arrayOf(
        DetailsCategory(
            "Материал",
            arrayOf(
                DetailsSubcategory(
                    null,
                    arrayOf(
                        DetailsItem("Название", "Композит Э-133-01-4026"),
                        DetailsItem("Дата поступления", "5 апреля 2017"),
                        DetailsItem("Партия", "Б-03-100-12"),
                        DetailsItem("Завод", "Завод “Березка”"),
                    ),
                ),
            ),
        ),
        DetailsCategory(
            "Стержень",
            arrayOf(
                DetailsSubcategory(
                    null,
                    arrayOf(
                        DetailsItem("Материал", "Береза"),
                    )
                ),
                DetailsSubcategory(
                    "Форма и размеры",
                    arrayOf(
                        DetailsItem("Размеры", "500мм x 0.5мм"),
                        DetailsItem("Внутренний диаметр", "-"),
                    )
                ),
                DetailsSubcategory(
                    "Физические характеристики",
                    arrayOf(
                        DetailsItem("Модуль упругости", "71000 МПа"),
                        DetailsItem("Скорость звука", "5050 м/с"),
                    )
                ),
            ),
        ),
    )

    Row (
        modifier = Modifier.padding(start = 14.dp, end = 14.dp)
    ) {
        Column {
            for (data in data) {
                Row {
                    Group(data.name, data.subcategories)

                }
            }
        }
    }

}

@Composable
fun Group(name: String, content: Array<DetailsSubcategory>) {
    Column {
        Text(name,
            modifier = Modifier.padding(top = 8.dp, bottom = 5.dp),
            style = TextStyle(
                color = Color(0xFF000000),
                fontFamily = montserratFont600,
                fontSize = 18.sp,
            ),
        )

        for (subcategory in content) {
            if (subcategory.name != null) {
                Text(subcategory.name,
                    modifier = Modifier.padding(top = 6.dp, bottom = 4.dp),
                    style = TextStyle(
                        color = Color(0xFF929292),
                        fontFamily = montserratFont500,
                        fontSize = 18.sp,
                    ),
                )
            }
            for (detailsItem in subcategory.content){
                Row (modifier = Modifier.padding(bottom = 3.dp)){
                    Text(detailsItem.name,
                        modifier = Modifier.width(240.dp),
                        style = TextStyle(
                            color = Color(0xFF2C3E50),
                            fontFamily = montserratFont500,
                            fontSize = 18.sp,
                        ),
                    )
                    Text(detailsItem.value,
                        style = TextStyle(
                            color = Color(0xFF2C3E50),
                            fontFamily = montserratFont400,
                            fontSize = 18.sp,
                        ),
                    )
                }
            }
        }
    }
}

class DetailsCategory(
    val name: String,
    val subcategories: Array<DetailsSubcategory>,
)

class DetailsSubcategory(
    val name: String?,
    val content: Array<DetailsItem>,
)
data class DetailsItem(
    val name: String,
    val value: String,
)