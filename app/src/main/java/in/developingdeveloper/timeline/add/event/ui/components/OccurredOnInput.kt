package `in`.developingdeveloper.timeline.add.event.ui.components

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import `in`.developingdeveloper.timeline.R
import `in`.developingdeveloper.timeline.core.ui.components.TimelineOutlinedTextField
import `in`.developingdeveloper.timeline.core.utils.toEpochMilli
import `in`.developingdeveloper.timeline.core.utils.toLocalDate
import `in`.developingdeveloper.timeline.core.utils.toLocalDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun OccurredOnInput(
    value: LocalDateTime,
    errorMessage: String?,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val occurredOnInteractionSource = remember { MutableInteractionSource() }
    val occurredInteractionSourceState by occurredOnInteractionSource.collectIsPressedAsState()

    var openDatePickerDialog by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = value.toEpochMilli(),
    )

    LaunchedEffect(key1 = occurredInteractionSourceState) {
        if (occurredInteractionSourceState) {
            if (!openDatePickerDialog) openDatePickerDialog = true
        }
    }

    if (openDatePickerDialog) {
        OccurredOnDatePickerDialog(
            datePickerState = datePickerState,
            onDismiss = { openDatePickerDialog = false },
            onConfirm = {
                openDatePickerDialog = false
                val selectedDateInMillis =
                    datePickerState.selectedDateMillis ?: return@OccurredOnDatePickerDialog

                val selectedDate = selectedDateInMillis.toLocalDate()
                Log.e("OccurredOnInput", "OccurredOnInput, selectedDate: $selectedDate")
                onValueChange(selectedDate.toLocalDateTime().toString())
            },
        )
    }

    Column(modifier = modifier) {
        OccurredOnLabel()

        Spacer(modifier = Modifier.height(4.dp))

        OccurredOnInput(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            errorMessage = errorMessage,
            openDatePickerDialog = openDatePickerDialog,
            occurredOnInteractionSource = occurredOnInteractionSource,
        )
    }
}

@Composable
private fun OccurredOnLabel() {
    Text(
        text = stringResource(id = R.string.occurred_on),
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
private fun OccurredOnInput(
    value: LocalDateTime,
    onValueChange: (String) -> Unit,
    enabled: Boolean,
    errorMessage: String?,
    openDatePickerDialog: Boolean,
    occurredOnInteractionSource: MutableInteractionSource,
) {
    var openDatePickerDialog1 = openDatePickerDialog
    TimelineOutlinedTextField(
        text = value.formatDateForUI(),
        onTextChange = onValueChange,
        enabled = enabled,
        errorMessage = errorMessage,
        trailingIcon = {
            IconButton(
                onClick = {
                    if (!openDatePickerDialog1) {
                        openDatePickerDialog1 = true
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

private fun LocalDateTime.formatDateForUI(): String {
    val datTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
    return this.format(datTimeFormatter) ?: this.toString()
}
