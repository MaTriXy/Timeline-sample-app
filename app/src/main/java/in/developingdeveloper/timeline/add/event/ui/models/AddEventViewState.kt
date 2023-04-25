package `in`.developingdeveloper.timeline.add.event.ui.models

import `in`.developingdeveloper.timeline.taglist.ui.models.TagListViewState

data class AddEventViewState(
    val form: NewEventForm,
    val tagListViewState: TagListViewState,
    val modifyTags: Boolean,
    val formEnabled: Boolean,
    val isLoading: Boolean,
    val isCompleted: Boolean,
) {
    companion object {
        val Initial = AddEventViewState(
            form = NewEventForm.Initial,
            tagListViewState = TagListViewState.Initial,
            modifyTags = false,
            formEnabled = true,
            isLoading = false,
            isCompleted = false,
        )
    }
}
