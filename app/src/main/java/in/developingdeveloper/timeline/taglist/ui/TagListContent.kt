package `in`.developingdeveloper.timeline.taglist.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import `in`.developingdeveloper.timeline.R
import `in`.developingdeveloper.timeline.core.ui.components.BackNavigationIcon
import `in`.developingdeveloper.timeline.core.ui.components.TimelineStartAlignedTopAppBar
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme
import `in`.developingdeveloper.timeline.taglist.ui.components.TagList
import `in`.developingdeveloper.timeline.taglist.ui.models.TagListViewState
import `in`.developingdeveloper.timeline.taglist.ui.models.UITag

@Composable
fun TagListContent(
    viewState: TagListViewState,
    onNavigationIconClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TimelineStartAlignedTopAppBar(
                title = stringResource(id = R.string.tags),
                navigationIcon = {
                    BackNavigationIcon(onNavigationIconClick)
                },
            )
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
        ) {
            TagList(tags = viewState.tags)
        }
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
private fun TagListContentPreview() {
    val tags = (1..20).map { UITag("Random Tag #$it") }

    val viewState = TagListViewState.Initial.copy(tags = tags)

    TimelineTheme {
        Surface {
            TagListContent(
                viewState = viewState,
                onNavigationIconClick = {},
            )
        }
    }
}
