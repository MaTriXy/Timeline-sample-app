package `in`.developingdeveloper.timeline.eventlist.domain.repositories

import `in`.developingdeveloper.timeline.eventlist.domain.datasource.EventsDataSource
import `in`.developingdeveloper.timeline.eventlist.domain.models.Event
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultEventListRepository @Inject constructor(
    private val eventsDataSource: EventsDataSource,
) : EventListRepository {
    override fun getAllEvents(): Flow<List<Event>> {
        return eventsDataSource.getAllEvents()
    }
}
