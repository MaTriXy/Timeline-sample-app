package `in`.developingdeveloper.timeline.add.tag.domain.usecases

import `in`.developingdeveloper.timeline.add.tag.domain.repositories.AddTagRepository
import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import javax.inject.Inject

@Suppress("TooGenericExceptionCaught", "TooGenericExceptionThrown")
class DefaultAddTagUseCase @Inject constructor(
    private val addTagRepository: AddTagRepository,
) : AddTagUseCase {
    override suspend operator fun invoke(tag: Tag): Result<Unit> {
        return try {
            validate(tag)

            addTagRepository.addTag(tag)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    private fun validate(tag: Tag) {
        if (tag.id.isBlank()) throw Exception("Id is required.")

        if (tag.label.isBlank()) throw Exception("Label is required.")
    }
}
