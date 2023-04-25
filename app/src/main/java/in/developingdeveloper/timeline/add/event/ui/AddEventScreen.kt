package `in`.developingdeveloper.timeline.add.event.ui

import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@Destination
fun AddEventScreen(
    navigator: DestinationsNavigator,
    resultNavigator: ResultBackNavigator<String>,
    viewModel: AddEventViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val modalBottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = viewState.isCompleted) {
        if (!viewState.isCompleted) return@LaunchedEffect

        resultNavigator.navigateBack(
            result = "Event added successfully!",
            onlyIfResumed = true,
        )
    }

    viewState.modifyTags.let { modifyTags ->
        LaunchedEffect(key1 = modifyTags) {
            if (!modifyTags) return@LaunchedEffect

            modalBottomSheetState.show()
        }
    }

    DisposableEffect(key1 = Unit) {
        onDispose {
            hideModalBottomSheet(
                coroutineScope = coroutineScope,
                modalBottomSheetState = modalBottomSheetState,
                onCompletion = viewModel::onModifyTagsCompleted,
            )
        }
    }

    AddEventContent(
        modalBottomSheetState = modalBottomSheetState,
        viewState = viewState,
        onTitleValueChange = viewModel::onTitleValueChange,
        onOccurredValueChange = viewModel::onOccurredValueChange,
        onModifyTagsClick = viewModel::onModifyTagsClick,
        onAddClick = viewModel::onAddEventClick,
        onCancelClick = { onCancelClick(navigator) },
        onBottomModalSheetDismiss = {
            hideModalBottomSheet(
                coroutineScope = coroutineScope,
                modalBottomSheetState = modalBottomSheetState,
                onCompletion = viewModel::onModifyTagsCompleted,
            )
        },
    )
}

private fun onCancelClick(navigator: DestinationsNavigator) {
    navigator.navigateUp()
}

private fun hideModalBottomSheet(
    coroutineScope: CoroutineScope,
    modalBottomSheetState: SheetState,
    onCompletion: () -> Unit,
) {
    coroutineScope.launch {
        modalBottomSheetState.hide()
    }.invokeOnCompletion {
        if (!modalBottomSheetState.isVisible) {
            onCompletion()
        }
    }
}
