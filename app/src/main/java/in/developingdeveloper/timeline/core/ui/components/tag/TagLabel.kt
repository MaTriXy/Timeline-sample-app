package `in`.developingdeveloper.timeline.core.ui.components.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import `in`.developingdeveloper.timeline.core.ui.theme.TagBackground

@Composable
fun TagLabel(
    label: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = label,
        modifier = modifier
            .padding(4.dp)
            .background(
                color = if (isSystemInDarkTheme()) {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                } else {
                    TagBackground
                },
                shape = RoundedCornerShape(28.dp),
            )
            .padding(horizontal = 20.dp, vertical = 2.dp),
    )
}
