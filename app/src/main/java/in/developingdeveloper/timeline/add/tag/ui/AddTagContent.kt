package `in`.developingdeveloper.timeline.add.tag.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.developingdeveloper.timeline.add.tag.ui.models.AddTagViewState
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme

@Composable
fun AddTagContent(
    viewState: AddTagViewState,
    onLabelValueChange: (String) -> Unit,
    onAddClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AddTagForm(
        form = viewState.form,
        onLabelValueChange = onLabelValueChange,
        onAddClick = onAddClick,
        onCancelClick = onCancelClick,
        modifier = modifier.padding(16.dp),
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
private fun AddTagContentPreview() {
    val viewState = AddTagViewState.Initial

    TimelineTheme {
        Surface {
            AddTagContent(
                viewState = viewState,
                onLabelValueChange = {},
                onAddClick = {},
                onCancelClick = {},
            )
        }
    }
}
