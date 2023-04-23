package `in`.developingdeveloper.timeline.core.data.local.events

import `in`.developingdeveloper.timeline.core.data.local.tags.PersistableTag
import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import `in`.developingdeveloper.timeline.eventlist.domain.datasource.EventsDataSource
import `in`.developingdeveloper.timeline.eventlist.domain.models.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomEventsDataSource(
    private val eventDao: EventDao,
) : EventsDataSource {
    override fun getAllEvents(): Flow<List<Event>> {
        return eventDao.getAllEvents()
            .map(List<PersistableEventWithTags>::toEvents)
    }
}

private fun List<PersistableEventWithTags>.toEvents(): List<Event> {
    return this.map {
        val event = it.event
        val tags = it.tags.toTags()
        Event(
            id = event.id,
            title = event.title,
            tags = tags,
            date = event.date,
            createdOn = event.createdOn,
        )
    }
}

private fun List<PersistableTag>.toTags(): List<Tag> {
    return this.map(PersistableTag::toTag)
}

private fun PersistableTag.toTag(): Tag {
    return Tag(
        id = this.id,
        label = this.label,
    )
}
