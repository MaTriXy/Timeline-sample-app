package `in`.developingdeveloper.timeline.core.data.local.tags

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersistableTag(
    @PrimaryKey
    val id: String,
    val label: String,
)
