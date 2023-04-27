package `in`.developingdeveloper.timeline.add.event.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.developingdeveloper.timeline.R
import `in`.developingdeveloper.timeline.add.event.ui.models.SelectableTagListViewState
import `in`.developingdeveloper.timeline.add.event.ui.models.SelectableUITag
import `in`.developingdeveloper.timeline.core.ui.components.tag.TagLabel
import `in`.developingdeveloper.timeline.core.ui.theme.TimelineTheme

@Composable
fun TagListForBottomSheet(
    viewState: SelectableTagListViewState,
    onTagClick: (SelectableUITag) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            LoadingTagListContent(
                isLoading = viewState.isLoading,
                isRefreshing = viewState.isRefreshing,
            )

            val message = viewState.message
            if (!viewState.isLoading && message != null) {
                Text(
                    text = message,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }

        if (viewState.shouldDisplayTags) {
            Text(
                text = stringResource(id = R.string.tags),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleLarge,
            )

            SelectableTagList(
                tags = viewState.tags,
                onTagClick = onTagClick,
            )
        }
    }
}

@Composable
private fun LoadingTagListContent(
    isLoading: Boolean,
    isRefreshing: Boolean,
) {
    when {
        isRefreshing -> {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        isLoading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(),
            )
        }

        else -> {
            Box(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun SelectableTagList(
    tags: List<SelectableUITag>,
    onTagClick: (SelectableUITag) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(tags) { tag ->
            SelectableTagListItem(
                tag = tag,
                onTagClick = onTagClick,
            )
        }
    }
}

@Composable
private fun SelectableTagListItem(
    tag: SelectableUITag,
    onTagClick: (SelectableUITag) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable(onClick = { onTagClick(tag) })
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        TagLabel(label = tag.label)

        Divider(
            color = Color.LightGray.copy(alpha = 0.4f),
            modifier = Modifier
                .padding(top = 8.dp),
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
private fun TagListForBottomSheetPreview() {
    val viewState = SelectableTagListViewState.Initial

    TimelineTheme {
        Surface {
            TagListForBottomSheet(
                viewState = viewState,
                onTagClick = {},
            )
        }
    }
}
