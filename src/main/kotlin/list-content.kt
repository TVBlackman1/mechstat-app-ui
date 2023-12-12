import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import internal.*
import repository.experiments.ListResponse
import repository.repository

@Composable
fun ListContent(modifier: Modifier) {
    var page by remember { mutableStateOf(1) }
    var experiments by remember {
        mutableStateOf<ListResponse?>(null)
    }
    LaunchedEffect(page) {
        experiments = repository.experiments.getExperiments(page, 16)
    }
    val columnWidth = 640

    Row (modifier = modifier) {
        Section(Width(columnWidth)) {
            SubSection(Height(724)) {
                Column {
                    ListComponent(experiments?.data, Modifier.weight(1f))
                    Row(
                        modifier = Modifier.width(columnWidth.dp),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        IconButton(
                            content = {Icon(Icons.Default.KeyboardArrowLeft, "left")},
                            onClick = {
                                page--
                            },
                            enabled = page != 1
                        )
                        IconButton(
                            content = {Icon(Icons.Default.KeyboardArrowRight, "right")},
                            onClick = {
                                page++
                            },
                            enabled = experiments != null && experiments!!.meta.allPages != page
                        )
                    }
                }
            }
            SubSection(MaxHeight()) {
                Text("Other info")
            }
        }
        Section(MaxWidth()) {
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

                        }
                    }
                }
            }
        }
    }
}