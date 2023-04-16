package `in`.developingdeveloper.timeline.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.developingdeveloper.timeline.core.domain.tags.datasources.DemoTagDataSource
import `in`.developingdeveloper.timeline.core.domain.tags.datasources.TagDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TagsModule {

    @Binds
    @Singleton
    abstract fun bindTagDataSource(
        tagDataSource: DemoTagDataSource,
    ): TagDataSource
}
