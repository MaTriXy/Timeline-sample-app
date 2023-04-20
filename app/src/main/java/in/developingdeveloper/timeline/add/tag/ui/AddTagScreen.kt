package `in`.developingdeveloper.timeline.add.tag.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator

@Composable
@Destination
fun AddTagScreen(
    navigator: DestinationsNavigator,
    resultNavigator: ResultBackNavigator<String>,
    viewModel: AddTagViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(viewState.isCompleted) {
        resultNavigator.navigateBack(
            result = "Tag added successfully!",
            onlyIfResumed = true,
        )
    }

    AddTagContent(
        viewState = viewState,
        onLabelValueChange = viewModel::onLabelValueChange,
        onAddClick = {
            keyboardController?.hide()
            viewModel.onAddClick()
        },
        onCancelClick = { onCancelClick(navigator) },
    )
}

private fun onCancelClick(navigator: DestinationsNavigator) {
    navigator.navigateUp()
}
