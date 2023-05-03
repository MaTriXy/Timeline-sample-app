package `in`.developingdeveloper.timeline.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.developingdeveloper.timeline.core.data.local.tags.RoomTagDataSource
import `in`.developingdeveloper.timeline.core.data.local.tags.TagDataSource
import `in`.developingdeveloper.timeline.core.domain.tags.repositories.DefaultTagRepository
import `in`.developingdeveloper.timeline.core.domain.tags.repositories.TagRepository
import `in`.developingdeveloper.timeline.modify.tag.domain.repositories.AddTagRepository
import `in`.developingdeveloper.timeline.modify.tag.domain.repositories.DefaultAddTagRepository
import `in`.developingdeveloper.timeline.modify.tag.domain.usecases.AddTagUseCase
import `in`.developingdeveloper.timeline.modify.tag.domain.usecases.DefaultAddTagUseCase
import `in`.developingdeveloper.timeline.taglist.domain.usecases.DefaultGetAllTagsUseCase
import `in`.developingdeveloper.timeline.taglist.domain.usecases.GetAllTagsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TagsModule {

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

    @Binds
    @Singleton
    abstract fun bindTagDataSource(
        roomTagDataSource: RoomTagDataSource,
    ): TagDataSource

    @Binds
    @Singleton
    abstract fun bindAddTagRepository(
        addTagRepository: DefaultAddTagRepository,
    ): AddTagRepository

    @Binds
    @Singleton
    abstract fun bindAddTagUseCase(
        addTagUseCase: DefaultAddTagUseCase,
    ): AddTagUseCase
}
