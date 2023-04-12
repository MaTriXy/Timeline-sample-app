package `in`.developingdeveloper.timeline.eventlist.domain.models

import java.time.LocalDateTime

data class Event(
    val id: String,
    val title: String,
    val tags: List<String>,
    val date: LocalDateTime,
    val createdOn: LocalDateTime,
)
