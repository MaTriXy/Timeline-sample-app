package `in`.developingdeveloper.timeline.core.data.local.events

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Transaction
    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<PersistableEventWithTags>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEvent(event: PersistableEvent)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEventWithTag(event: EventTagCrossRef)

    @Transaction
    suspend fun addEventWithTags(eventWithTags: PersistableEventWithTags) {
        val (event, tags) = eventWithTags
        addEvent(event)

        tags
            .map {
                EventTagCrossRef(event.id, it.id)
            }.forEach {
                addEventWithTag(it)
            }
    }
}
