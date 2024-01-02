package sub_sections

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import repository.experiments.dto.DetailsSubcategory
import repository.experiments.dto.ExperimentDetailsResponse
import repository.repository
import java.io.File

@Composable
fun ExperimentDetailsSubSection(experimentId: Int) {
    SubSection(MaxHeight()) {

        var details by remember {
            mutableStateOf<ExperimentDetailsResponse?>(null)
        }
        LaunchedEffect(experimentId) {
            details = repository.experiments.getExperimentDetails(experimentId)
        }
        details?.let {
            Column {
                DetailsHeader(details!!.date)
                ChartPart()
                TabView(
                    TabGroup("Основное") { DetailsInfo(details!!) },
                    TabGroup("Связанное") { Text("Связанные эксперименты") },
                    TabGroup("Заказчик") { Text("Информация о заказчике") },
                )
            }
        }
    }
}

@Composable
fun DetailsHeader(header: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            header,
            style = TextStyle(
                color = Color(0xFF2C3E50),
                fontFamily = montserratFont400,
                fontSize = 16.sp,
            ),
            modifier = Modifier.padding(top = 12.dp, bottom = 6.dp)
        )
    }
}
@Composable
fun ChartPart() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.width(820.dp).height(400.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                DropDownButton("Осцилограмма", listOf("Осцилограмма", "Другое"))
                DropDownButton("Загрузить", listOf("CSV", "PNG"))
            }
            Box(
                modifier = Modifier
                    .background(Color(0xFFD2E9E9), RoundedCornerShape(6.dp))
            ) {
                val file =
                    File("C:\\Users\\vbifm\\IdeaProjects\\mechstat\\src\\main\\resources\\images\\chart-example.png")
                val imageBitmap: ImageBitmap = remember(file) {
                    loadImageBitmap(file.inputStream())
                }
                Column {
                    Image(
                        painter = BitmapPainter(image = imageBitmap),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun TabView(vararg tabs: TabGroup) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .width(984.dp)
                .height(480.dp)
                .padding(top = 6.dp)
                .background(Color(0xFFF9F9F9), RoundedCornerShape(4.dp))
        ) {
            var tabIndex by remember { mutableStateOf(0) }

            val tabNames = tabs.map { tab -> tab.name }

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                        TabRow(
                            selectedTabIndex = tabIndex,
                            backgroundColor = Color.Transparent,
                            modifier = Modifier.width(600.dp),
                            indicator = {},
                            divider = {},
                        ) {
                            tabNames.forEachIndexed { index, title ->
                                Tab(
                                    text = {
                                        Text(
                                            title,
                                            style = TextStyle(
                                                color = if (tabIndex == index) Color(0xFF000000) else Color(0xFFA7A7A7),
                                                fontFamily = montserratFont600,
                                                fontSize = 14.sp,
                                            ),
                                        )
                                    },
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
                tabs[tabIndex].view()
            }
        }
    }
}

class TabGroup(
    val name: String,
    val view: @Composable () -> Unit
)

@Preview
@Composable
fun DetailsInfo(data: ExperimentDetailsResponse) {
    if (data == null) {
        return
    }
    Row(
        modifier = Modifier.padding(start = 14.dp, end = 14.dp)
    ) {
        Column {
            for (data in data.properties) {
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
        Text(
            name,
            modifier = Modifier.padding(top = 8.dp, bottom = 5.dp),
            style = TextStyle(
                color = Color(0xFF000000),
                fontFamily = montserratFont600,
                fontSize = 16.sp,
            ),
        )

        for (subcategory in content) {
            if (subcategory.name != null) {
                Text(
                    subcategory.name,
                    modifier = Modifier.padding(top = 6.dp, bottom = 4.dp),
                    style = TextStyle(
                        color = Color(0xFF929292),
                        fontFamily = montserratFont500,
                        fontSize = 16.sp,
                    ),
                )
            }
            for (detailsItem in subcategory.content) {
                Row(modifier = Modifier.padding(bottom = 3.dp)) {
                    Text(
                        detailsItem.name,
                        modifier = Modifier.width(250.dp),
                        style = TextStyle(
                            color = Color(0xFF2C3E50),
                            fontFamily = montserratFont500,
                            fontSize = 16.sp,
                        ),
                    )
                    Text(
                        detailsItem.value,
                        style = TextStyle(
                            color = Color(0xFF2C3E50),
                            fontFamily = montserratFont400,
                            fontSize = 16.sp,
                        ),
                    )
                }
            }
        }
    }
}

@Composable
fun DropDownButton(text: String, variants: List<String>) {
    Column {
        var expanded by remember { mutableStateOf(false) }
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF1F8F8), contentColor = Color(0xFF2C3E50)),
            onClick = { expanded = !expanded },
            modifier = Modifier.width(190.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text, style = TextStyle(
                        fontFamily = montserratFont400,
                        fontSize = 16.sp,
                    )
                )
                Icon(Icons.Default.ArrowDropDown, "показать варианты")
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            for (variant in variants) {
                DropdownMenuItem(
                    onClick = {}
                ) {
                    Text(variant)
                }
            }
        }
    }
}
