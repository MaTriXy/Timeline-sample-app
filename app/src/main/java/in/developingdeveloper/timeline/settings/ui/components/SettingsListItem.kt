package `in`.developingdeveloper.timeline.settings.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.developingdeveloper.timeline.core.ui.models.UiText
import `in`.developingdeveloper.timeline.core.ui.models.getString
import `in`.developingdeveloper.timeline.core.ui.theme.Platinum
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme
import `in`.developingdeveloper.timeline.settings.ui.models.UiSetting

@Composable
fun SettingsListItem(
    setting: UiSetting,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = setting.onClick)
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Setting(setting)

        Divider(
            color = Color.LightGray.copy(alpha = 0.4f),
            modifier = Modifier
                .padding(top = 8.dp),
        )
    }
}

@Composable
private fun LeadingIcon(imageVector: ImageVector) {
    Icon(
        imageVector = imageVector,
        contentDescription = null,
        modifier = Modifier
            .background(
                color = if (isSystemInDarkTheme()) {
                    MaterialTheme.colorScheme.surface
                } else {
                    Platinum
                },
                shape = CircleShape,
            )
            .padding(8.dp),
    )
}

@Composable
private fun Label(
    label: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = label,
        modifier = modifier,
    )
}

@Composable
private fun Value(
    value: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = value,
        modifier = modifier,
        color = LocalContentColor.current.copy(alpha = 0.4f),
    )
}

@Composable
private fun NavigateNextIcon() {
    Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
}

@Composable
private fun Setting(
    setting: UiSetting,
) {
    when (setting) {
        is UiSetting.WithNavigation -> NavigationItem(setting = setting)
        is UiSetting.WithValue -> LabelAndValueItem(setting)
    }
}

@Composable
private fun NavigationItem(
    setting: UiSetting.WithNavigation,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        LeadingIcon(setting.leadingIcon)

        Spacer(modifier = Modifier.width(8.dp))

        Label(label = setting.label.getString())

        Spacer(modifier = Modifier.weight(1f))

        NavigateNextIcon()
    }
}

@Composable
private fun LabelAndValueItem(
    setting: UiSetting.WithValue,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Label(label = setting.label.getString())

        Spacer(modifier = Modifier.weight(1f))

        Value(value = setting.value.getString())
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember", "MagicNumber")
private fun SettingsListItemPreview() {
    val withNavigation = UiSetting.WithNavigation(
        label = UiText.StringText("Sample"),
        leadingIcon = Icons.Outlined.Sell,
        onClick = {},
    )

    val valueItem = UiSetting.WithValue(
        label = UiText.StringText("Version"),
        value = UiText.StringText("1.2.3"),
    )

    TimelineTheme {
        Surface {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp),
            ) {
                SettingsListItem(setting = withNavigation)
                SettingsListItem(setting = valueItem)
            }
        }
    }
}
