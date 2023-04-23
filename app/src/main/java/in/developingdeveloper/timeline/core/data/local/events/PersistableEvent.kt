package `in`.developingdeveloper.timeline.core.data.local.events

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "events")
data class PersistableEvent(
    @PrimaryKey
    val id: String,
    val title: String,
    val date: LocalDateTime,
    val createdOn: LocalDateTime,
)
