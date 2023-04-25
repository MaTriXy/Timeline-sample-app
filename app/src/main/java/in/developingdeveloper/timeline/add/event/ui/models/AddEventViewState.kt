package `in`.developingdeveloper.timeline.add.event.ui.models

data class AddEventViewState(
    val form: NewEventForm,
    val formEnabled: Boolean,
) {
    companion object {
        val Initial = AddEventViewState(
            form = NewEventForm.Initial,
            formEnabled = true,
        )
    }
}
