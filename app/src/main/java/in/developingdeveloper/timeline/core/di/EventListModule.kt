package `in`.developingdeveloper.timeline.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `in`.developingdeveloper.timeline.core.data.local.events.RoomEventsDataSource
import `in`.developingdeveloper.timeline.eventlist.domain.datasource.EventsDataSource
import `in`.developingdeveloper.timeline.eventlist.domain.repositories.DefaultEventListRepository
import `in`.developingdeveloper.timeline.eventlist.domain.repositories.EventListRepository
import `in`.developingdeveloper.timeline.eventlist.domain.usescases.DefaultGetAllEventsUseCase
import `in`.developingdeveloper.timeline.eventlist.domain.usescases.GetAllEventsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EventListModule {

    @Binds
    @Singleton
    abstract fun bindEventListRepository(
        eventListRepository: DefaultEventListRepository,
    ): EventListRepository

    @Binds
    @Singleton
    abstract fun bindEventListDataSource(
        eventsDataSource: RoomEventsDataSource,
    ): EventsDataSource

    @Binds
    @Singleton
    abstract fun bindGetAllEventsUseCase(
        getAllEventsUseCase: DefaultGetAllEventsUseCase,
    ): GetAllEventsUseCase
}
