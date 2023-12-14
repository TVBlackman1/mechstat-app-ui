import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import internal.NoRippleTheme

data class Button(
    val text: String,
    val icon: ImageVector,
    val desc: String,
    val contentType: ContentType
)

class LeftSideContent(
    val top: Array<Button>,
    val bottom: Array<Button>,
)

typealias onContentType = (type: ContentType) -> Unit

fun isSelected(button: Button, contentType: ContentType): Boolean =
    button.contentType == contentType

@Composable
fun LeftSide(content: LeftSideContent, contentType: ContentType,
             pick: onContentType) {

    val selectedBackgroundShape = RoundedCornerShape(4.dp)
    val displayButton: @Composable (Button)->Unit = {
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        NavigationRailItem(
            modifier = Modifier.hoverable(interactionSource),
            icon = {
                Icon(it.icon, it.desc, modifier =
                if (isSelected(it, contentType))
                    Modifier.background(Color(0xFFDCDCDC), selectedBackgroundShape)
                else if (isHovered)
                    Modifier.background(Color(0xFFEBEBEB), selectedBackgroundShape)
                else
                    Modifier.background(Color(0x00000000), selectedBackgroundShape)
                )
                   },
            label = { Text(it.text) },
            selected = isSelected(it, contentType),
            unselectedContentColor = Color(0xFF2C3E50),
            selectedContentColor = Color(0xFF2C3E50),
            onClick = {
                pick(it.contentType)
            }
        )
    }
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        NavigationRail(modifier = Modifier
            .background(Color(0xFFF8F6F4)).width(54.dp),
            elevation = 0.dp,
        ) {
            for (button in content.top) {
                displayButton(button)
            }
            Spacer(modifier = Modifier.weight(1.0f))
            for (button in content.bottom) {
                displayButton(button)
            }
        }
    }
}

fun getLeftSideContent(): LeftSideContent {
    return LeftSideContent(
        top = arrayOf(
            Button(
                text = "new",
                icon = Icons.Default.PlayArrow,
                desc= "Start new",
                contentType = ContentType.NEW,
            ),
            Button(
                text = "list",
                icon = Icons.Default.List,
                desc= "Experiments",
                contentType = ContentType.LIST,
            ),
        ),
        bottom = arrayOf(
            Button(
                text = "debug",
                icon = Icons.Default.Settings,
                desc= "Debug settings",
                contentType = ContentType.SETTINGS,
            ),
        ),
    )
}