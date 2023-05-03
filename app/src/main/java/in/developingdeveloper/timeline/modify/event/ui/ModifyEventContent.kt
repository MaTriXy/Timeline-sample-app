package `in`.developingdeveloper.timeline.modify.event.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.developingdeveloper.timeline.R
import `in`.developingdeveloper.timeline.core.ui.components.TimelineStartAlignedTopAppBar
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme
import `in`.developingdeveloper.timeline.modify.event.ui.components.tags.bottomsheet.TagListBottomSheetContent
import `in`.developingdeveloper.timeline.modify.event.ui.models.ModifyEventViewState
import `in`.developingdeveloper.timeline.modify.event.ui.models.SelectableUITag

@Composable
fun ModifyEventContent(
    modalBottomSheetState: SheetState,
    snackbarHostState: SnackbarHostState,
    viewState: ModifyEventViewState,
    onTitleValueChange: (String) -> Unit,
    onOccurredValueChange: (String) -> Unit,
    onModifyTagsClick: () -> Unit,
    onAddClick: () -> Unit,
    onCancelClick: () -> Unit,
    onBottomModalSheetDismiss: () -> Unit,
    onTagClick: (Int, SelectableUITag) -> Unit,
) {
    Scaffold(
        topBar = {
            TimelineStartAlignedTopAppBar(title = stringResource(id = R.string.add_event))
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .padding(16.dp),
        ) {
            if (viewState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(),
                )
            }

            ModifyEventForm(
                isFormEnabled = viewState.formEnabled,
                form = viewState.form,
                onTitleValueChange = onTitleValueChange,
                onOccurredOnValueChange = onOccurredValueChange,
                onModifyTagsClick = onModifyTagsClick,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Actions(
                onAddClick = onAddClick,
                onCancelClick = onCancelClick,
            )
        }
    }

    if (viewState.modifyTags) {
        ModalBottomSheet(
            onDismissRequest = onBottomModalSheetDismiss,
            sheetState = modalBottomSheetState,
        ) {
            TagListBottomSheetContent(
                viewState = viewState.tagListViewState,
                onTagClick = onTagClick,
            )
        }
    }
}

@Composable
private fun Actions(
    onAddClick: () -> Unit,
    onCancelClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        OutlinedButton(
            onClick = onCancelClick,
            modifier = Modifier.weight(1f),
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }

        Button(
            onClick = onAddClick,
            modifier = Modifier.weight(2f),
        ) {
            Text(
                text = stringResource(id = R.string.add_event),
            )
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
private fun ModifyEventContentPreview() {
    val viewState = ModifyEventViewState.Initial

    TimelineTheme {
        Surface {
            ModifyEventContent(
                modalBottomSheetState = rememberModalBottomSheetState(),
                snackbarHostState = SnackbarHostState(),
                viewState = viewState,
                onTitleValueChange = {},
                onOccurredValueChange = {},
                onModifyTagsClick = {},
                onCancelClick = {},
                onAddClick = {},
                onBottomModalSheetDismiss = {},
                onTagClick = { _, _ -> },
            )
        }
    }
}
