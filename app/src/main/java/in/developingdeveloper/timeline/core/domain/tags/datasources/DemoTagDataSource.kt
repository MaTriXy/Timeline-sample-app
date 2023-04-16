package `in`.developingdeveloper.timeline.core.domain.tags.datasources

import `in`.developingdeveloper.timeline.core.domain.tags.models.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class DemoTagDataSource @Inject constructor() : TagDataSource {
    private val tags = listOf(Tag("Android"), Tag("Kotlin"))

    override fun getTags(): Flow<List<Tag>> {
        return flowOf(tags)
    }
}
