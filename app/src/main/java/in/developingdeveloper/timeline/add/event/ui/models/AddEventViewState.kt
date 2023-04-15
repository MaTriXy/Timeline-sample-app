package `in`.developingdeveloper.timeline.add.event.ui.models

data class AddEventViewState(
    val form: NewEventForm,
) {
    companion object {
        val Initial = AddEventViewState(NewEventForm.Initial)
    }
}
