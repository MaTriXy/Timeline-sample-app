package `in`.developingdeveloper.timeline.add.tag.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun AddTagScreen(
    navigator: DestinationsNavigator,
    viewModel: AddTagViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    AddTagContent(
        viewState = viewState,
        onLabelValueChange = viewModel::onLabelValueChange,
        onAddClick = viewModel::onAddClick,
        onCancelClick = { onCancelClick(navigator) },
    )
}

private fun onCancelClick(navigator: DestinationsNavigator) {
    navigator.navigateUp()
}
