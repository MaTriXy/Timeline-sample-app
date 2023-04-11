package `in`.developingdeveloper.timeline.eventlist.ui.models

data class EventListViewState(
    val events: List<UIEvent>,
    val loading: Boolean,
) {
    companion object {
        val Initial = EventListViewState(
            events = emptyList(),
            loading = false,
        )
    }
}
