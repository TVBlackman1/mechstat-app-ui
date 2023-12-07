package internal

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

abstract class SectionWidth() {
    abstract fun perform(modifier: Modifier): Modifier
}

class Width(private val size: Int): SectionWidth() {
    override fun perform(modifier: Modifier): Modifier {
        return modifier.width(size.dp)
    }
}

class MaxWidth: SectionWidth() {
    override fun perform(modifier: Modifier): Modifier {
        return modifier.fillMaxWidth()
    }
}

// TODO try to create this
//class RemainWidth(part: Float): SectionWidth() {
//    override fun perform(modifier: Modifier): Modifier {
//        return modifier.weight(part)
//    }
//}
@Composable
fun Section(widthRule: SectionWidth, child: @Composable () -> Unit) {
    Column(
        modifier = widthRule.perform(
            Modifier
                .padding(top = 4.dp, bottom = 4.dp)
                .padding(start = 2.dp, end = 2.dp)
                .fillMaxHeight()
        )
    ) { child() }
}