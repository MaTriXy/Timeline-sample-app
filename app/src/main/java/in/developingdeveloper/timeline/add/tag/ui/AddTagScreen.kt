package `in`.developingdeveloper.timeline.add.tag.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewState.isCompleted) {
        resultNavigator.navigateBack(
            result = "Tag added successfully!",
            onlyIfResumed = true,
        )
    }

    val errorMessage = viewState.errorMessage
    LaunchedEffect(key1 = errorMessage) {
        if (errorMessage == null) return@LaunchedEffect

        snackbarHostState.showSnackbar(errorMessage)
    }

    AddTagContent(
        snackbarHostState = snackbarHostState,
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
