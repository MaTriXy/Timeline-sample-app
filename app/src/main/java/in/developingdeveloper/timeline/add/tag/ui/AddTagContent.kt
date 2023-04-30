package `in`.developingdeveloper.timeline.add.tag.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.developingdeveloper.timeline.R
import `in`.developingdeveloper.timeline.add.tag.ui.models.AddTagViewState
import `in`.developingdeveloper.timeline.core.ui.components.BackNavigationIcon
import `in`.developingdeveloper.timeline.core.ui.components.TimelineStartAlignedTopAppBar
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme

@Composable
fun AddTagContent(
    snackbarHostState: SnackbarHostState,
    viewState: AddTagViewState,
    onNavigationIconClick: () -> Unit,
    onLabelValueChange: (String) -> Unit,
    onAddClick: () -> Unit,
    onCancelClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            AddTagTopBar(onNavigationIconClick)
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { paddingValues ->
        AddTagForm(
            form = viewState.form,
            onLabelValueChange = onLabelValueChange,
            onAddClick = onAddClick,
            onCancelClick = onCancelClick,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
        )
    }
}

@Composable
private fun AddTagTopBar(onNavigationIconClick: () -> Unit) {
    TimelineStartAlignedTopAppBar(
        title = stringResource(id = R.string.add_tag),
        navigationIcon = {
            BackNavigationIcon(onNavigationIconClick = onNavigationIconClick)
        },
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
                snackbarHostState = SnackbarHostState(),
                viewState = viewState,
                onNavigationIconClick = {},
                onLabelValueChange = {},
                onAddClick = {},
                onCancelClick = {},
            )
        }
    }
}
