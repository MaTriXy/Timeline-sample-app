package `in`.developingdeveloper.timeline.add.event.ui.models

import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class NewEventForm(
    val title: String,
    val titleErrorMessage: String?,
    val tags: List<Tag>,
    val occurredOn: LocalDateTime,
    val occurredOnErrorMessage: String?,
) {
    companion object {
        val Initial = NewEventForm(
            title = "",
            titleErrorMessage = null,
            tags = emptyList(),
            occurredOn = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT),
            occurredOnErrorMessage = null,
        )
    }
}
