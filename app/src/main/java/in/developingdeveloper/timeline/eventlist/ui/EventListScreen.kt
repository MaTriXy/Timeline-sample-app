package `in`.developingdeveloper.timeline.eventlist.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import `in`.developingdeveloper.timeline.destinations.SettingsScreenDestination

@Composable
@Destination
@RootNavGraph(start = true)
fun EventListScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: EventListViewModel = hiltViewModel(),
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(key1 = viewState.errorMessage) {
        viewState.errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    EventListContent(
        viewState = viewState,
        onAddNewEventClick = {},
        onSettingsClick = { onSettingsClick(navigator) },
        modifier = modifier,
    )
}

private fun onSettingsClick(navigator: DestinationsNavigator) {
    navigator.navigate(SettingsScreenDestination)
}
