package internal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val shape = RoundedCornerShape(0.dp)

abstract class SubSectionHeight() {
    abstract fun perform(modifier: Modifier): Modifier
}

class Height(private val size: Int): SubSectionHeight() {
    override fun perform(modifier: Modifier): Modifier {
        return modifier.height(size.dp)
    }
}

class MaxHeight: SubSectionHeight() {
    override fun perform(modifier: Modifier): Modifier {
        return modifier.fillMaxHeight()
    }
}
@Composable
fun SubSection(heightRule: SubSectionHeight, child: @Composable () -> Unit) {
    Row (
        modifier =  heightRule.perform(Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 2.dp)
            .background(Color.White, shape)
            .padding(top = 3.dp, bottom = 3.dp, start = 4.dp, end = 4.dp)
        )
    ) { child() }
}