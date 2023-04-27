package `in`.developingdeveloper.timeline.add.event.domain.usecases

import `in`.developingdeveloper.timeline.core.domain.event.models.Event

interface AddEventUseCase {
    suspend operator fun invoke(event: Event): Result<Unit>
}