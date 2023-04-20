package `in`.developingdeveloper.timeline.add.tag.domain.usecases

import `in`.developingdeveloper.timeline.add.tag.domain.repositories.AddTagRepository
import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import javax.inject.Inject

@Suppress("TooGenericExceptionCaught")
class DefaultAddTagUseCase @Inject constructor(
    private val addTagRepository: AddTagRepository,
) : AddTagUseCase {
    override suspend operator fun invoke(tag: Tag): Result<Unit> {
        return try {
            addTagRepository.addTag(tag)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
