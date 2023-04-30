package `in`.developingdeveloper.timeline.add.event.domain.usecases

import `in`.developingdeveloper.timeline.add.event.domain.repositories.AddEventRepository
import `in`.developingdeveloper.timeline.core.domain.event.models.Event
import java.time.LocalDateTime
import javax.inject.Inject

@Suppress("TooGenericExceptionCaught", "TooGenericExceptionThrown")
class DefaultAddEventUseCase @Inject constructor(
    private val addEventRepository: AddEventRepository,
) : AddEventUseCase {
    override suspend fun invoke(event: Event): Result<Unit> {
        return try {
            validateEvent(event)

            addEventRepository.addEvent(event)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    private fun validateEvent(event: Event) {
        if (event.title.isBlank()) throw Exception("Title is required.")

        if (event.date.isAfter(LocalDateTime.now())) throw Exception("Event must be in past.")
    }
}
