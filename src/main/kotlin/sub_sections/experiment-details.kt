package sub_sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import internal.MaxHeight
import internal.SubSection
import montserratFont400

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