package `in`.developingdeveloper.timeline.add.tag.domain.repositories

import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag

interface AddTagRepository {
    suspend fun addTag(tag: Tag)
}
