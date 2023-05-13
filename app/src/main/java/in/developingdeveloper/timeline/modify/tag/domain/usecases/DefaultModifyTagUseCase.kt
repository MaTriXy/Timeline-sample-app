package `in`.developingdeveloper.timeline.modify.tag.domain.usecases

import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import `in`.developingdeveloper.timeline.modify.tag.domain.exceptions.ModifyTagException
import `in`.developingdeveloper.timeline.modify.tag.domain.repositories.AddTagRepository
import javax.inject.Inject

@Suppress("TooGenericExceptionCaught", "TooGenericExceptionThrown")
class DefaultModifyTagUseCase @Inject constructor(
    private val addTagRepository: AddTagRepository,
) : ModifyTagUseCase {
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
        if (tag.id.isBlank()) throw ModifyTagException.InvalidIdException("Id is required.")

        if (tag.label.isBlank()) {
            throw ModifyTagException.InvalidLabelException("Label is required.")
        }
    }
}
