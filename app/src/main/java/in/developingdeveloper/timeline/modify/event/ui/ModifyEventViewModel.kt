package `in`.developingdeveloper.timeline.modify.event.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.developingdeveloper.timeline.core.domain.event.models.Event
import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import `in`.developingdeveloper.timeline.modify.event.domain.usecases.ModifyEventUseCase
import `in`.developingdeveloper.timeline.modify.event.ui.models.ModifyEventForm
import `in`.developingdeveloper.timeline.modify.event.ui.models.ModifyEventViewState
import `in`.developingdeveloper.timeline.modify.event.ui.models.SelectableTagListViewState
import `in`.developingdeveloper.timeline.modify.event.ui.models.SelectableUITag
import `in`.developingdeveloper.timeline.taglist.domain.usecases.GetAllTagsUseCase
import `in`.developingdeveloper.timeline.taglist.ui.models.UITag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ModifyEventViewModel @Inject constructor(
    private val modifyEventUseCase: ModifyEventUseCase,
    private val getAllTagsUseCase: GetAllTagsUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(ModifyEventViewState.Initial)
    val viewState = _viewState.asStateFlow()

    private var isNewEvent: Boolean = true

    private var areTagsLoaded = false

    init {
        observeViewStateForIsNewEvent()
    }

    private fun observeViewStateForIsNewEvent() {
        viewState
            .map { it.isNewEvent }
            .distinctUntilChanged()
            .onEach { isNewEvent = it }
            .launchIn(viewModelScope)
    }

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
        lazilyFetchTags()

        _viewState.update { it.copy(modifyTags = true) }
    }

    private fun lazilyFetchTags() {
        if (areTagsLoaded) return

        areTagsLoaded = true
        getAllTags()
    }

    private fun getAllTags() {
        getAllTagsUseCase.invoke()
            .distinctUntilChanged()
            .onStart {
                _viewState.update {
                    val updatedTagListViewState = it.tagListViewState.toLoading()
                    it.copy(tagListViewState = updatedTagListViewState)
                }
            }
            .onEach { result ->
                _viewState.update {
                    getAddEventViewStateForResult(result)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun getAddEventViewStateForResult(result: Result<List<Tag>>): ModifyEventViewState {
        val currentAddEventViewState = _viewState.value

        val updatedTagListViewState =
            getTagListViewStateForResult(
                result = result,
                selectedTags = currentAddEventViewState.form.tags,
                currentTagListViewState = currentAddEventViewState.tagListViewState,
            )

        return currentAddEventViewState.copy(
            tagListViewState = updatedTagListViewState,
        )
    }

    private fun getTagListViewStateForResult(
        result: Result<List<Tag>>,
        selectedTags: Set<UITag>,
        currentTagListViewState: SelectableTagListViewState,
    ): SelectableTagListViewState {
        return result.fold(
            onSuccess = { tags ->
                val uiTags = tags.toSelectableUiTags()
                currentTagListViewState.toLoaded(
                    selectedTags = selectedTags,
                    selectableTags = uiTags,
                )
            },
            onFailure = {
                val message = it.message ?: "Something went wrong."
                currentTagListViewState.toError(errorMessage = message)
            },
        )
    }

    fun onDoneClick() {
        viewModelScope.launch {
            _viewState.update { it.copy(isLoading = true, formEnabled = false) }

            val eventToCreate = _viewState.value.form.toEvent()

            val result = modifyEventUseCase.invoke(eventToCreate, isNewEvent)
            _viewState.value = getViewStateForModifyEventResult(result)

            _viewState.update { it.copy(isLoading = false, formEnabled = true) }
        }
    }

    private fun getViewStateForModifyEventResult(result: Result<Unit>): ModifyEventViewState {
        val currentViewState = _viewState.value

        return result.fold(
            onSuccess = {
                currentViewState.copy(
                    isCompleted = true,
                )
            },
            onFailure = {
                val defaultErrorMessage =
                    if (isNewEvent) "Error storing event." else "Error updating event"

                val errorMessage = it.message ?: defaultErrorMessage
                currentViewState.copy(
                    isCompleted = false,
                    errorMessage = errorMessage,
                )
            },
        )
    }

    fun onModifyTagsCompleted() {
        _viewState.update { it.copy(modifyTags = false) }
    }

    fun onTagClick(index: Int, tag: SelectableUITag) {
        _viewState.update {
            val uiTag = tag.toUITag()
            val isAlreadySelected = it.form.tags.contains(uiTag)
            val updatedTags = if (isAlreadySelected) {
                it.form.tags - uiTag
            } else {
                it.form.tags + uiTag
            }

            val updatedSelectableTag = tag.copy(isSelected = !isAlreadySelected)
            val selectableTags = it.tagListViewState.tags
            val updatedSelectableTags = selectableTags.toMutableList()
            updatedSelectableTags[index] = updatedSelectableTag

            val updatedForm = it.form.copy(tags = updatedTags)
            it.copy(
                form = updatedForm,
                tagListViewState = it.tagListViewState.copy(tags = updatedSelectableTags),
            )
        }
    }
}

private fun ModifyEventForm.toEvent(): Event {
    return Event(
        id = UUID.randomUUID().toString(),
        title = this.title,
        tags = this.tags.toTags(),
        date = this.occurredOn,
        createdOn = LocalDateTime.now(),
    )
}

private fun Set<UITag>.toTags(): List<Tag> {
    return this.map(UITag::toTag)
}

private fun UITag.toTag(): Tag {
    return Tag(
        id = this.id,
        label = this.label,
    )
}

private fun SelectableUITag.toUITag(): UITag {
    return UITag(
        id = this.id,
        label = this.label,
    )
}

private fun List<Tag>.toSelectableUiTags(): List<SelectableUITag> {
    return this.map(Tag::toSelectableUiTag)
}

private fun Tag.toSelectableUiTag(): SelectableUITag {
    return SelectableUITag(
        id = this.id,
        label = this.label,
        isSelected = false,
    )
}
