package `in`.developingdeveloper.timeline.add.event.ui.components

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import `in`.developingdeveloper.timeline.add.event.ui.models.SelectableTagListViewState
import `in`.developingdeveloper.timeline.add.event.ui.models.SelectableUITag
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme

@Composable
fun TagListBottomSheetContent(
    viewState: SelectableTagListViewState,
    onTagClick: (SelectableUITag) -> Unit,
) {
    TagListForBottomSheet(
        viewState = viewState,
        onTagClick = onTagClick,
    )
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
    val tags = (1..10).map {
        SelectableUITag(
            id = it.toString(),
            label = "Tag $it",
            isSelected = false,
        )
    }

    val viewState = SelectableTagListViewState.Initial.copy(tags = tags)

    TimelineTheme {
        Surface {
            TagListBottomSheetContent(
                viewState = viewState,
                onTagClick = {},
            )
        }
    }
}
