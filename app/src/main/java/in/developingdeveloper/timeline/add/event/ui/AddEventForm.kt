package `in`.developingdeveloper.timeline.add.event.ui

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.developingdeveloper.timeline.R
import `in`.developingdeveloper.timeline.add.event.ui.models.NewEventForm
import `in`.developingdeveloper.timeline.core.ui.components.TimelineOutlinedTextField
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme
import `in`.developingdeveloper.timeline.core.utils.toEpochMilli
import `in`.developingdeveloper.timeline.core.utils.toLocalDate
import `in`.developingdeveloper.timeline.core.utils.toLocalDateTime
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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

        val occurredOnInteractionSource = remember { MutableInteractionSource() }
        val occurredInteractionSourceState by occurredOnInteractionSource.collectIsPressedAsState()

        var openDatePickerDialog by remember { mutableStateOf(false) }

        LaunchedEffect(key1 = occurredInteractionSourceState) {
            if (occurredInteractionSourceState) {
                if (!openDatePickerDialog) openDatePickerDialog = true
            }
        }

        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = form.occurredOn.toEpochMilli(),
        )

        if (openDatePickerDialog) {
            DatePickerDialog(
                onDismissRequest = { openDatePickerDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDatePickerDialog = false
                            val selectedDateInMillis =
                                datePickerState.selectedDateMillis ?: return@TextButton
                            val selectedDate = selectedDateInMillis.toLocalDate()

                            onOccurredOnValueChange(selectedDate.toLocalDateTime().toString())
                        },
                    ) {
                        Text(text = "Confirm")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { openDatePickerDialog = false },
                    ) {
                        Text(text = "Cancel")
                    }
                },
            ) {
                DatePicker(
                    state = datePickerState,
                    dateValidator = { utcDateInMills ->
                        val selectableDate = Instant.ofEpochMilli(utcDateInMills).atZone(
                            ZoneId.systemDefault(),
                        ).toLocalDate()
                        val tomorrow = LocalDate.now().plusDays(1) ?: return@DatePicker false

                        selectableDate.isBefore(tomorrow)
                    },
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(id = R.string.occurred_on),
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(4.dp))

        TimelineOutlinedTextField(
            text = form.occurredOn.formatDateForUI(),
            onTextChange = onOccurredOnValueChange,
            enabled = form.formEnabled,
            errorMessage = form.occurredOnErrorMessage,
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (!openDatePickerDialog) {
                            openDatePickerDialog = true
                        }
                    },
                ) {
                    Icon(imageVector = Icons.Default.Event, contentDescription = null)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next,
            ),
            singleLine = true,
            readOnly = true,
            interactionSource = occurredOnInteractionSource,
        )
    }
}

private val datTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")

private fun LocalDateTime.formatDateForUI(): String {
    return this.format(datTimeFormatter) ?: this.toString()
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
