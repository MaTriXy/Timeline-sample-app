package `in`.developingdeveloper.timeline.core.domain.tags.datasources

import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import kotlinx.coroutines.flow.Flow

interface TagDataSource {

    fun getTags(): Flow<List<Tag>>
}
