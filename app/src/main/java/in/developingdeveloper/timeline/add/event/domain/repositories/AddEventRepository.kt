package `in`.developingdeveloper.timeline.add.event.domain.repositories

import `in`.developingdeveloper.timeline.core.domain.event.models.Event

interface AddEventRepository {
    suspend fun addEvent(event: Event)
}
