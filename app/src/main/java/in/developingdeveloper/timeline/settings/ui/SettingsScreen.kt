package `in`.developingdeveloper.timeline.settings.ui

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import `in`.developingdeveloper.timeline.R
import `in`.developingdeveloper.timeline.core.ui.components.onBackNavigationIconClick
import `in`.developingdeveloper.timeline.core.ui.models.UiText
import `in`.developingdeveloper.timeline.destinations.TagListScreenDestination
import `in`.developingdeveloper.timeline.settings.ui.models.UiSetting

@Composable
@Destination
fun SettingsScreen(navigator: DestinationsNavigator) {
    val context = LocalContext.current

    val versionName = getVersionName(context).toString()
    val settings = remember { getUiSettings(versionName, navigator) }

    SettingsContent(
        settings = settings,
        onBackNavigationIconClick = { onBackNavigationIconClick(navigator) },
    )
}

private fun getUiSettings(
    versionName: String,
    navigator: DestinationsNavigator,
): List<UiSetting> {
    return listOf(
        UiSetting.WithNavigation(
            label = UiText.ResourceText(value = R.string.tags),
            leadingIcon = Icons.Outlined.Sell,
            onClick = {
                navigator.navigate(TagListScreenDestination)
            },
        ),
        UiSetting.WithValue(
            label = UiText.ResourceText(value = R.string.app_version),
            value = UiText.StringText(value = versionName),
            leadingIcon = Icons.Default.Numbers,
        ),
    )
}

@Suppress("SwallowedException")
private fun getVersionName(context: Context) = try {
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    packageInfo.versionName
} catch (exception: PackageManager.NameNotFoundException) {
    null
}
