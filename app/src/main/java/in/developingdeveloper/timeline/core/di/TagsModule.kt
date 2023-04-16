package `in`.developingdeveloper.timeline.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.developingdeveloper.timeline.core.domain.tags.datasources.DemoTagDataSource
import `in`.developingdeveloper.timeline.core.domain.tags.datasources.TagDataSource
import `in`.developingdeveloper.timeline.core.domain.tags.repositories.DefaultTagRepository
import `in`.developingdeveloper.timeline.core.domain.tags.repositories.TagRepository
import `in`.developingdeveloper.timeline.taglist.domain.usecases.DefaultGetAllTagsUseCase
import `in`.developingdeveloper.timeline.taglist.domain.usecases.GetAllTagsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TagsModule {

    @Binds
    @Singleton
    abstract fun bindTagDataSource(
        tagDataSource: DemoTagDataSource,
    ): TagDataSource

    @Binds
    @Singleton
    abstract fun bindTagRepository(
        tagRepository: DefaultTagRepository,
    ): TagRepository

    @Binds
    @Singleton
    abstract fun bindGetAllTagsUseCase(
        getAllTagsUseCase: DefaultGetAllTagsUseCase,
    ): GetAllTagsUseCase
}
