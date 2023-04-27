package `in`.developingdeveloper.timeline.add.event.ui.components

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme
import `in`.developingdeveloper.timeline.taglist.ui.models.TagListViewState
import `in`.developingdeveloper.timeline.taglist.ui.models.UITag

@Composable
fun TagListBottomSheetContent(
    viewState: TagListViewState,
) {
    TagListForBottomSheet(viewState = viewState)
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
private fun TagListBottomSheetPreview() {
    val tags = (1..10).map { UITag("Tag $it") }

    val viewState = TagListViewState.Initial.copy(tags = tags)

    TimelineTheme {
        Surface {
            TagListBottomSheetContent(viewState = viewState)
        }
    }
}
