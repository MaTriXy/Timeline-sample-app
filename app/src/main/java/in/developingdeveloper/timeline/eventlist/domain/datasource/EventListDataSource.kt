package `in`.developingdeveloper.timeline.eventlist.domain.datasource

import `in`.developingdeveloper.timeline.eventlist.domain.models.Event
import kotlinx.coroutines.flow.Flow

interface EventListDataSource {
    fun getAllEvents(): Flow<List<Event>>
}
