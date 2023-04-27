package `in`.developingdeveloper.timeline.add.event.ui.models

data class AddEventViewState(
    val form: NewEventForm,
    val tagListViewState: SelectableTagListViewState,
    val modifyTags: Boolean,
    val formEnabled: Boolean,
    val isLoading: Boolean,
    val isCompleted: Boolean,
) {
    companion object {
        val Initial = AddEventViewState(
            form = NewEventForm.Initial,
            tagListViewState = SelectableTagListViewState.Initial,
            modifyTags = false,
            formEnabled = true,
            isLoading = false,
            isCompleted = false,
        )
    }
}
