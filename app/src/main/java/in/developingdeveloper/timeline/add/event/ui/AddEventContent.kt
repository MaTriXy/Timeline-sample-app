package `in`.developingdeveloper.timeline.add.event.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.developingdeveloper.timeline.R
import `in`.developingdeveloper.timeline.add.event.ui.models.AddEventViewState
import `in`.developingdeveloper.timeline.core.ui.components.TimelineStartAlignedTopAppBar
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme

@Composable
fun AddEventContent(
    viewState: AddEventViewState,
    onTitleValueChange: (String) -> Unit,
    onOccurredValueChange: (String) -> Unit,
    onAddClick: () -> Unit,
    onCancelClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TimelineStartAlignedTopAppBar(title = stringResource(id = R.string.add_event))
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .padding(16.dp),
        ) {
            AddEventForm(
                isFormEnabled = viewState.formEnabled,
                form = viewState.form,
                onTitleValueChange = onTitleValueChange,
                onOccurredOnValueChange = onOccurredValueChange,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Actions(
                onAddClick = onAddClick,
                onCancelClick = onCancelClick,
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
private fun AddEventContentPreview() {
    val viewState = AddEventViewState.Initial

    TimelineTheme {
        Surface {
            AddEventContent(
                viewState = viewState,
                onTitleValueChange = {},
                onOccurredValueChange = {},
                onCancelClick = {},
                onAddClick = {},
            )
        }
    }
}
