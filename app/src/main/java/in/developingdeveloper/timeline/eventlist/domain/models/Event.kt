package `in`.developingdeveloper.timeline.eventlist.domain.models

import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import java.time.LocalDateTime

data class Event(
    val id: String,
    val title: String,
    val tags: List<Tag>,
    val date: LocalDateTime,
    val createdOn: LocalDateTime,
)
