package sub_sections

import ListComponent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import internal.Height
import internal.SubSection
import repository.experiments.ListResponse
import repository.repository

@Composable
fun ListSubSection(height: Int) {
    var page by remember { mutableStateOf(1) }
    var experiments by remember {
        mutableStateOf<ListResponse?>(null)
    }
    LaunchedEffect(page) {
        experiments = repository.experiments.getExperiments(page, 16)
    }

    SubSection(Height(height)) {
        Column {
            ListComponent(experiments?.data, Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(
                    content = { Icon(Icons.Default.KeyboardArrowLeft, "left") },
                    onClick = {
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