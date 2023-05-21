package `in`.developingdeveloper.timeline.settings.ui.models

import androidx.compose.ui.graphics.vector.ImageVector
import `in`.developingdeveloper.timeline.core.ui.models.UiText

sealed class UiSetting(
    open val label: UiText,
    open val onClick: () -> Unit = {},
    open val leadingIcon: ImageVector? = null,
) {
    data class WithNavigation(
        override val label: UiText,
        override val leadingIcon: ImageVector,
        override val onClick: () -> Unit,
    ) : UiSetting(
        label = label,
        onClick = onClick,
        leadingIcon = leadingIcon,
    )

    data class WithValue(
        override val label: UiText,
        val value: UiText,
    ) : UiSetting(
        label = label,
    )
}
