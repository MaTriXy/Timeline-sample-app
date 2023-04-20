package `in`.developingdeveloper.timeline.core.data.local.tags

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class PersistableTag(
    @PrimaryKey
    val id: String,
    val label: String,
)
