package ua.glebm.smartwaste.ui.screen.map

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.glebm.smartwaste.core.android.BaseViewModel
import ua.glebm.smartwaste.core.android.stateReducerFlow
import ua.glebm.smartwaste.domain.usecase.recycle.GetRecyclePointUseCase
import ua.glebm.smartwaste.ui.screen.map.model.RecyclerClusterItem
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getRecyclePointUseCase: GetRecyclePointUseCase,
) : BaseViewModel() {

    val state = stateReducerFlow(
        initialState = MapScreenState(),
        reduceState = ::handleEvent,
    )

    init {
        getRecyclePoints()
    }

    private fun getRecyclePoints() = viewModelScope.launch {
        val list = getRecyclePointUseCase()
            .getOrDefault(emptyList())
            .map { RecyclerClusterItem(it) }
        val event = MapScreenEvent.ClusterPointsLoaded(list)
        state.handleEvent(event)
    }

    private fun handleEvent(
        currentState: MapScreenState,
        event: MapScreenEvent,
    ): MapScreenState {
        return when (event) {
            is MapScreenEvent.ClusterPointsLoaded -> {
                currentState.copy(recyclePoints = event.recyclePoints)
            }
        }
    }
}
