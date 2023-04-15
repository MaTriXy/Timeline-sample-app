package `in`.developingdeveloper.timeline.add.event.ui.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class NewEventForm(
    val title: String,
    val titleErrorMessage: String?,
    val tags: List<String>,
    val occurredOn: LocalDateTime,
    val occurredOnErrorMessage: String?,
    val formEnabled: Boolean,
) {
    companion object {
        val Initial = NewEventForm(
            title = "",
            titleErrorMessage = null,
            tags = emptyList(),
            occurredOn = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT),
            occurredOnErrorMessage = null,
            formEnabled = true,
        )
    }
}
