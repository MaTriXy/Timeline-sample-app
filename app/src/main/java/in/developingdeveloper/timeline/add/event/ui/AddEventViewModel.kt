package `in`.developingdeveloper.timeline.add.event.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.developingdeveloper.timeline.add.event.domain.usecases.AddEventUseCase
import `in`.developingdeveloper.timeline.add.event.ui.models.AddEventViewState
import `in`.developingdeveloper.timeline.add.event.ui.models.NewEventForm
import `in`.developingdeveloper.timeline.core.domain.event.models.Event
import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import `in`.developingdeveloper.timeline.taglist.domain.usecases.GetAllTagsUseCase
import `in`.developingdeveloper.timeline.taglist.ui.models.TagListViewState
import `in`.developingdeveloper.timeline.taglist.ui.models.UITag
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val addEventUseCase: AddEventUseCase,
    private val getAllTagsUseCase: GetAllTagsUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddEventViewState.Initial)
    val viewState = _viewState.asStateFlow()

    private var areTagsLoaded = false

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

    private fun getAddEventViewStateForResult(result: Result<List<Tag>>): AddEventViewState {
        val currentAddEventViewState = _viewState.value

        val updatedTagListViewState =
            getTagListViewStateForResult(
                result = result,
                currentTagListViewState = currentAddEventViewState.tagListViewState,
            )

        return currentAddEventViewState.copy(
            tagListViewState = updatedTagListViewState,
        )
    }

    private fun getTagListViewStateForResult(
        result: Result<List<Tag>>,
        currentTagListViewState: TagListViewState,
    ): TagListViewState {
        return result.fold(
            onSuccess = { tags ->
                val uiTags = tags.toUiTags()
                currentTagListViewState.toLoaded(tags = uiTags)
            },
            onFailure = {
                val message = it.message ?: "Something went wrong."
                currentTagListViewState.toError(errorMessage = message)
            },
        )
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

    fun onTagClick(tag: UITag) {
        _viewState.update {
            val updatedTags = if (it.form.tags.contains(tag)) {
                it.form.tags - tag
            } else {
                it.form.tags + tag
            }

            val updatedForm = it.form.copy(tags = updatedTags)
            it.copy(form = updatedForm)
        }
    }
}

private fun NewEventForm.toEvent(): Event {
    return Event(
        id = UUID.randomUUID().toString(),
        title = this.title,
        tags = this.tags.toTags(),
        date = this.occurredOn,
        createdOn = LocalDateTime.now(),
    )
}

private fun List<UITag>.toTags(): List<Tag> {
    return this.map(UITag::toTag)
}

private fun UITag.toTag(): Tag {
    return Tag(
        id = this.id,
        label = this.label,
    )
}

private fun List<Tag>.toUiTags(): List<UITag> {
    return this.map(Tag::toUiTag)
}

private fun Tag.toUiTag(): UITag {
    return UITag(
        id = this.id,
        label = this.label,
    )
}
