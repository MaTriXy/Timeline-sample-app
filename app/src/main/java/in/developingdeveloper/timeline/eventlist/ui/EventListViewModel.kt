package `in`.developingdeveloper.timeline.eventlist.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.developingdeveloper.timeline.eventlist.ui.models.EventListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class EventListViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(EventListViewState.Initial)
    val viewState = _viewState.asStateFlow()
}
