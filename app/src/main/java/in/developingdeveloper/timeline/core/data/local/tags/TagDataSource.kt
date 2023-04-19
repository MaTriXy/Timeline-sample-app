package `in`.developingdeveloper.timeline.core.data.local.tags

import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag

interface TagDataSource {
    suspend fun insertTag(tag: Tag)
}
