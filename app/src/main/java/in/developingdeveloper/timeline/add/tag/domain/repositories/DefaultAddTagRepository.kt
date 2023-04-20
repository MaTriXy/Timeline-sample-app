package `in`.developingdeveloper.timeline.add.tag.domain.repositories

import `in`.developingdeveloper.timeline.core.Result
import `in`.developingdeveloper.timeline.core.data.local.tags.TagDataSource
import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import javax.inject.Inject

class DefaultAddTagRepository @Inject constructor(
    private val tagDataSource: TagDataSource,
) : AddTagRepository {
    override suspend fun addTag(tag: Tag): Result<Unit> {
        tagDataSource.insertTag(tag)
        return Result.Success(Unit)
    }
}
