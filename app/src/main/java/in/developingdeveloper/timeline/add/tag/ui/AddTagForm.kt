package `in`.developingdeveloper.timeline.add.tag.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import `in`.developingdeveloper.timeline.R
import `in`.developingdeveloper.timeline.add.tag.ui.models.NewTagForm
import `in`.developingdeveloper.timeline.core.ui.components.FormInput
import `in`.developingdeveloper.timeline.core.ui.components.TimelineOutlinedTextField
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme

@Composable
fun AddTagForm(
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
    val form = NewTagForm(label = "")

    TimelineTheme {
        Surface {
            AddTagForm(
                form = form,
                onLabelValueChange = {},
            )
        }
    }
}
