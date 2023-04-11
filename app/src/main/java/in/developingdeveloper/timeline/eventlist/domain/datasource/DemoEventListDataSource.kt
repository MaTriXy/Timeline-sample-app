package `in`.developingdeveloper.timeline.eventlist.domain.datasource

import `in`.developingdeveloper.timeline.eventlist.domain.models.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDateTime
import javax.inject.Inject

class DemoEventListDataSource @Inject constructor() : EventListDataSource {

    private val events = listOf(
        Event(
            id = "1",
            title = "Started new app - Timeline.",
            tags = listOf("Android", "Kotlin"),
            date = LocalDateTime.now(),
            createdOn = LocalDateTime.now(),
        ),
        Event(
            id = "2",
            title = "Demo event",
            tags = emptyList(),
            date = LocalDateTime.now(),
            createdOn = LocalDateTime.now(),
        ),
    )

    override fun getAllEvents(): Flow<List<Event>> = flowOf(events)
}
