package sub_sections

import ListComponent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
import internal.Height
import internal.SubSection
import montserratFont400
import repository.experiments.dto.ListResponse
import repository.repository

@Composable
fun ListSubSection(height: Int, onChangeData: (dataId: Int) -> Unit) {
    var page by remember { mutableStateOf(1) }
    var experiments by remember {
        mutableStateOf<ListResponse?>(null)
    }
    val windowState: WindowState = rememberWindowState(size = DpSize.Unspecified)
    LaunchedEffect(page) {
        experiments = repository.experiments.getExperiments(page, 18)
    }

    SubSection(Height(height)) {
        Column {
            Text("Эксперименты",
                style = TextStyle(
                    color = Color(0xFF2C3E50),
                    fontFamily = montserratFont400,
                    fontSize = 16.sp,
                ),
                modifier = Modifier.padding(top=12.dp, bottom = 6.dp)
                )
            ListComponent(experiments?.data, Modifier.weight(1f), onChangeData)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(
                    content = { Icon(Icons.Default.KeyboardArrowLeft, "left") },
                    onClick = {
                        println(windowState.size)
                        page--
                    },
                    enabled = page != 1
                )
                IconButton(
                    content = { Icon(Icons.Default.KeyboardArrowRight, "right") },
                    onClick = {
                        page++
                    },
                    enabled = experiments != null && experiments!!.meta.allPages != page
                )
            }
        }
    }
}