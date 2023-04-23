package `in`.developingdeveloper.timeline.core.data.local.events

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Transaction
    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<PersistableEventWithTags>>
}
