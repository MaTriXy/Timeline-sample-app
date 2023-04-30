package `in`.developingdeveloper.timeline.add.tag.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.developingdeveloper.timeline.add.tag.domain.usecases.AddTagUseCase
import `in`.developingdeveloper.timeline.add.tag.ui.models.AddTagViewState
import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddTagViewModel @Inject constructor(
    private val addTagUseCase: AddTagUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddTagViewState.Initial)
    val viewState = _viewState.asStateFlow()

    private fun getViewStateForAddTagResult(result: Result<Unit>): AddTagViewState {
        val currentViewState = _viewState.value

        return result.fold(
            onSuccess = {
                currentViewState.copy(
                    isLoading = false,
                    isFormEnabled = false,
                    isCompleted = true,
                )
            },
            onFailure = {
                val message = it.message ?: "Something went wrong."

                currentViewState.copy(
                    isLoading = false,
                    isFormEnabled = true,
                    errorMessage = message,
                )
            },
        )
    }

    fun onLabelValueChange(label: String) {
        _viewState.update {
            val updatedForm = it.form.copy(label = label)
            it.copy(form = updatedForm)
        }
    }

    fun onAddClick() {
        _viewState.update { it.copy(isLoading = true, isFormEnabled = false) }

        val tagId = UUID.randomUUID().toString()
        val label = _viewState.value.form.label

        val tagToCreate = Tag(
            id = tagId,
            label = label,
        )

        viewModelScope.launch {
            val result = addTagUseCase.invoke(tagToCreate)
            _viewState.value = getViewStateForAddTagResult(result)
        }
    }
}
