package `in`.developingdeveloper.timeline.eventlist.domain.repositories

import `in`.developingdeveloper.timeline.eventlist.domain.models.Event
import kotlinx.coroutines.flow.Flow

interface EventListRepository {
    fun getAllEvents(): Flow<List<Event>>
}
