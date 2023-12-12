import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import internal.*
import sub_sections.ExperimentDetailsSubSection
import sub_sections.ListSubSection

@Composable
fun ListContent(modifier: Modifier) {
    val columnWidth = 640

    Row (modifier = modifier) {
        Section(Width(columnWidth)) {
            ListSubSection(724)
            SubSection(MaxHeight()) {
                Text("Other info")
            }
        }
        Section(MaxWidth()) {
            ExperimentDetailsSubSection()
        }
    }
}