package `in`.developingdeveloper.timeline.add.event.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import `in`.developingdeveloper.timeline.R
import `in`.developingdeveloper.timeline.core.ui.theme.TagBackground

@Composable
fun TagsInput(
    tags: List<String>,
    modifier: Modifier = Modifier,
) {
    FormInput(
        label = stringResource(R.string.tags),
        input = {
            TagsInputContent(tags)
        },
        modifier = modifier,
    )
}

@Composable
private fun TagsInputContent(tags: List<String>) {
    OutlinedCard(
        shape = RoundedCornerShape(4.0.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = Color.Transparent),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp),
        ) {
            if (tags.isNotEmpty()) {
                Tags(
                    tags = tags,
                    modifier = Modifier.weight(1f),
                )
            } else {
                NoTagsText(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 16.dp),
                )
            }

            TagsActionButton(tags)
        }
    }
}

@Composable
private fun Tags(
    tags: List<String>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier
            .padding(12.dp),
    ) {
        tags.forEach {
            Text(
                text = it,
                modifier = Modifier
                    .padding(4.dp)
                    .background(TagBackground, RoundedCornerShape(28.dp))
                    .padding(horizontal = 20.dp, vertical = 2.dp),
            )
        }
    }
}

@Composable
private fun NoTagsText(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "None yet",
        modifier = modifier,
    )
}

@Composable
private fun TagsActionButton(tags: List<String>) {
    IconButton(
        onClick = {},
        modifier = Modifier
            .padding(vertical = 4.dp),
    ) {
        val icon = if (tags.isEmpty()) {
            Icons.Default.Add
        } else {
            Icons.Default.Edit
        }

        Icon(imageVector = icon, contentDescription = null)
    }
}
