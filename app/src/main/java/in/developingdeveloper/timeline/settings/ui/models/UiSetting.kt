package `in`.developingdeveloper.timeline.settings.ui.models

import androidx.compose.ui.graphics.vector.ImageVector
import `in`.developingdeveloper.timeline.core.ui.models.UiText

data class UiSetting(
    val label: UiText,
    val onClick: () -> Unit,
    val leadingIcon: ImageVector? = null,
)
