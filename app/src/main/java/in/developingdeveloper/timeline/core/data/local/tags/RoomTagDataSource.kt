package `in`.developingdeveloper.timeline.core.data.local.tags

import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RoomTagDataSource @Inject constructor(
    private val tagDao: TagDao,
) : TagDataSource {
    override suspend fun insertTag(tag: Tag) {
        tagDao.insertTag(tag.toPersistableTag())
    }

    override fun getTags(): Flow<List<Tag>> {
        return flowOf(emptyList())
    }
}

private fun Tag.toPersistableTag(): PersistableTag {
    return PersistableTag(
        id = this.id,
        label = this.label,
    )
}
