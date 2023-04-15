package `in`.developingdeveloper.timeline.add.tag.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.developingdeveloper.timeline.R
import `in`.developingdeveloper.timeline.add.tag.ui.models.NewTagForm
import `in`.developingdeveloper.timeline.core.ui.components.FormInput
import `in`.developingdeveloper.timeline.core.ui.components.TimelineOutlinedTextField
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme

@Composable
fun AddTagForm(
    form: NewTagForm,
    onLabelValueChange: (String) -> Unit,
    onAddClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TagInput(
            form = form,
            onLabelValueChange = onLabelValueChange,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Actions(
            onAddClick = onAddClick,
            onCancelClick = onCancelClick,
        )
    }
}

@Composable
private fun TagInput(
    form: NewTagForm,
    onLabelValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    FormInput(
        label = stringResource(id = R.string.label),
        input = {
            TagLabelInput(
                label = form.label,
                onLabelValueChange = onLabelValueChange,
            )
        },
        modifier = modifier,
    )
}

@Composable
private fun TagLabelInput(
    label: String,
    onLabelValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TimelineOutlinedTextField(
        text = label,
        onTextChange = onLabelValueChange,
        modifier = modifier.fillMaxWidth(),
    )
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
                text = stringResource(id = R.string.add_tag),
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
private fun AddTagFormPreview() {
    val form = NewTagForm.Initial

    TimelineTheme {
        Surface {
            AddTagForm(
                form = form,
                onLabelValueChange = {},
                onAddClick = {},
                onCancelClick = {},
            )
        }
    }
}
