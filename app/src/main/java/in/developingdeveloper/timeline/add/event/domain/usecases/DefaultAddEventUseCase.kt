package `in`.developingdeveloper.timeline.add.event.domain.usecases

import `in`.developingdeveloper.timeline.add.event.domain.repositories.AddEventRepository
import `in`.developingdeveloper.timeline.core.domain.event.models.Event
import javax.inject.Inject

@Suppress("TooGenericExceptionCaught")
class DefaultAddEventUseCase @Inject constructor(
    private val addEventRepository: AddEventRepository,
) : AddEventUseCase {
    override suspend fun invoke(event: Event): Result<Unit> {
        return try {
            addEventRepository.addEvent(event)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
