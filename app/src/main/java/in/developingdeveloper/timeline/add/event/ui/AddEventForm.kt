package `in`.developingdeveloper.timeline.add.event.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.developingdeveloper.timeline.R
import `in`.developingdeveloper.timeline.add.event.ui.components.OccurredOnInput
import `in`.developingdeveloper.timeline.add.event.ui.models.NewEventForm
import `in`.developingdeveloper.timeline.core.ui.components.TimelineOutlinedTextField
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme

@Composable
fun AddEventForm(
    form: NewEventForm,
    onTitleValueChange: (String) -> Unit,
    onOccurredOnValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.title),
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(4.dp))

        TimelineOutlinedTextField(
            text = form.title,
            onTextChange = onTitleValueChange,
            enabled = form.formEnabled,
            errorMessage = form.titleErrorMessage,
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next,
            ),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(12.dp))

        OccurredOnInput(
            value = form.occurredOn,
            errorMessage = form.occurredOnErrorMessage,
            onValueChange = onOccurredOnValueChange,
            enabled = form.formEnabled,
        )
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
private fun AddEventFormPreview() {
    val form = NewEventForm.Initial

    TimelineTheme {
        Surface {
            AddEventForm(
                form = form,
                onTitleValueChange = {},
                onOccurredOnValueChange = {},
            )
        }
    }
}
