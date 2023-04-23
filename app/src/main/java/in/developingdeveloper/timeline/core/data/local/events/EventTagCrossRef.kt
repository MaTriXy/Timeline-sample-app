package `in`.developingdeveloper.timeline.core.data.local.events

import androidx.room.Entity

@Entity(primaryKeys = ["eventId", "tagId"])
data class EventTagCrossRef(
    val eventId: String,
    val tagId: String,
)
