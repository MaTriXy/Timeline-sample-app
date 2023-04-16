package `in`.developingdeveloper.timeline.taglist.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import `in`.developingdeveloper.timeline.core.ui.components.onBackNavigationIconClick

@Composable
@Destination
fun TagListScreen(
    navigator: DestinationsNavigator,
    viewModel: TagListViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    TagListContent(
        viewState = viewState,
        onNavigationIconClick = { onBackNavigationIconClick(navigator) },
    )
}
