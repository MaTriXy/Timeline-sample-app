package `in`.developingdeveloper.timeline.add.event.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator

@Composable
fun AddEventScreen(
    navigator: DestinationsNavigator,
    resultNavigator: ResultBackNavigator<String>,
    viewModel: AddEventViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewState.isCompleted) {
        if (!viewState.isCompleted) return@LaunchedEffect

        resultNavigator.navigateBack(
            result = "Event added successfully!",
            onlyIfResumed = true,
        )
    }

    AddEventContent(
        viewState = viewState,
        onTitleValueChange = viewModel::onTitleValueChange,
        onOccurredValueChange = viewModel::onOccurredValueChange,
        onAddClick = viewModel::onAddEventClick,
        onCancelClick = { onCancelClick(navigator) },
    )
}

private fun onCancelClick(navigator: DestinationsNavigator) {
    navigator.navigateUp()
}
