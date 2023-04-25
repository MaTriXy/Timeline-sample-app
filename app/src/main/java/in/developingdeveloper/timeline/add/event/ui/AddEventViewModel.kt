package `in`.developingdeveloper.timeline.add.event.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.developingdeveloper.timeline.add.event.domain.usecases.AddEventUseCase
import `in`.developingdeveloper.timeline.add.event.ui.models.AddEventViewState
import `in`.developingdeveloper.timeline.add.event.ui.models.NewEventForm
import `in`.developingdeveloper.timeline.core.domain.event.models.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val addEventUseCase: AddEventUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddEventViewState.Initial)
    val viewState = _viewState.asStateFlow()

    fun onTitleValueChange(title: String) {
        _viewState.update {
            val updatedForm = it.form.copy(title = title)
            it.copy(form = updatedForm)
        }
    }

    fun onOccurredValueChange(occurredOn: String) {
        _viewState.update {
            val updatedForm = it.form.copy(occurredOn = LocalDateTime.parse(occurredOn))
            it.copy(form = updatedForm)
        }
    }

    fun onModifyTagsClick() {
        _viewState.update { it.copy(modifyTags = true) }
    }

    fun onAddEventClick() {
        viewModelScope.launch {
            _viewState.update { it.copy(isLoading = true, formEnabled = false) }

            val eventToCreate = _viewState.value.form.toEvent()

            val result = addEventUseCase.invoke(eventToCreate)
            _viewState.value = getViewStateForAddEventResult(result)

            _viewState.update { it.copy(isLoading = false, formEnabled = true) }
        }
    }

    private fun getViewStateForAddEventResult(result: Result<Unit>): AddEventViewState {
        val currentViewState = _viewState.value

        return result.fold(
            onSuccess = {
                currentViewState.copy(
                    isCompleted = true,
                )
            },
            onFailure = {
                currentViewState.copy(
                    isCompleted = false,
                )
            },
        )
    }

    fun onModifyTagsCompleted() {
        _viewState.update { it.copy(modifyTags = false) }
    }
}

private fun NewEventForm.toEvent(): Event {
    return Event(
        id = UUID.randomUUID().toString(),
        title = this.title,
        tags = this.tags,
        date = this.occurredOn,
        createdOn = LocalDateTime.now(),
    )
}
