package `in`.developingdeveloper.timeline.core.domain.tags.repositories

import `in`.developingdeveloper.timeline.core.data.local.tags.TagDataSource
import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultTagRepository @Inject constructor(
    private val tagDataSource: TagDataSource,
) : TagRepository {

    override fun getAllTags(): Flow<List<Tag>> {
        return tagDataSource.getTags()
    }
}
