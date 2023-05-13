package `in`.developingdeveloper.timeline.modify.tag.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import `in`.developingdeveloper.timeline.modify.tag.domain.exceptions.ModifyTagException
import `in`.developingdeveloper.timeline.modify.tag.domain.usecases.ModifyTagUseCase
import `in`.developingdeveloper.timeline.modify.tag.ui.models.ModifyTagViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ModifyTagViewModel @Inject constructor(
    private val modifyTagUseCase: ModifyTagUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(ModifyTagViewState.Initial)
    val viewState = _viewState.asStateFlow()

    private fun getViewStateForModifyTagResult(result: Result<Unit>): ModifyTagViewState {
        val currentViewState = _viewState.value

        return result.fold(
            onSuccess = {
                currentViewState.copy(
                    isLoading = false,
                    isFormEnabled = false,
                    isCompleted = true,
                )
            },
            onFailure = { throwable ->

                when (throwable) {
                    is ModifyTagException.InvalidLabelException -> {
                        val updatedForm =
                            currentViewState.form.copy(labelErrorMessage = throwable.message)
                        currentViewState.copy(form = updatedForm)
                    }

                    else -> {
                        val message = throwable.message ?: "Something went wrong."

                        currentViewState.copy(
                            isLoading = false,
                            isFormEnabled = true,
                            errorMessage = message,
                        )
                    }
                }
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
            val result = modifyTagUseCase.invoke(tagToCreate)
            _viewState.value = getViewStateForModifyTagResult(result)
        }
    }
}
