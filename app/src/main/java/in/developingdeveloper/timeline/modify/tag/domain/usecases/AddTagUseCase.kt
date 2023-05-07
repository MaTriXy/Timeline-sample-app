package `in`.developingdeveloper.timeline.modify.tag.domain.usecases

import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag

interface AddTagUseCase {
    suspend operator fun invoke(tag: Tag): Result<Unit>
}
