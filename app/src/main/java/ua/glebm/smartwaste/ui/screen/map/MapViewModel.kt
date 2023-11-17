package ua.glebm.smartwaste.ui.screen.map

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.glebm.smartwaste.core.android.BaseViewModel
import ua.glebm.smartwaste.core.android.stateReducerFlow
import ua.glebm.smartwaste.domain.usecase.bucket.ClearBucketUseCase
import ua.glebm.smartwaste.domain.usecase.bucket.GetBucketCategoriesIdsUseCase
import ua.glebm.smartwaste.domain.usecase.recycle.GetRecyclePointUseCase
import ua.glebm.smartwaste.domain.usecase.recycle.GetRecyclePointsByCategoriesUseCase
import ua.glebm.smartwaste.ui.navigation.route.MapScreenRoute
import ua.glebm.smartwaste.ui.screen.map.model.RecyclerClusterItem
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getRecyclePointUseCase: GetRecyclePointUseCase,
    private val getRecyclePointsByCategoriesUseCase: GetRecyclePointsByCategoriesUseCase,
    private val clearBucketUseCase: ClearBucketUseCase,
    private val getBucketCategoriesIdsUseCase: GetBucketCategoriesIdsUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    val state = stateReducerFlow(
        initialState = MapScreenState(),
        reduceState = ::handleEvent,
    )

    private val enabled = savedStateHandle[MapScreenRoute.categoryEnabledArg] ?: false
    private val categoryIds = mutableListOf<Long>()

    init {
        getBucketCategoryIds()
        getRecyclePoints()
    }

    private fun getBucketCategoryIds() = viewModelScope.launch(Dispatchers.IO) {
        val list = getBucketCategoriesIdsUseCase().getOrDefault(emptyList())
        categoryIds.addAll(list)
    }

    private fun getRecyclePoints() = viewModelScope.launch {
        val list = if (enabled) {
            getRecyclePointsByCategoriesUseCase()
        } else {
            getRecyclePointUseCase()
        }.getOrDefault(emptyList()).map { RecyclerClusterItem(it) }

        val event = MapScreenEvent.ClusterPointsLoaded(list)
        state.handleEvent(event)
    }

    private fun clearBucket() = viewModelScope.launch {
        clearBucketUseCase()
    }

    private fun validatePointCategories(
        point: RecyclerClusterItem,
    ) = viewModelScope.launch(Dispatchers.IO) {
        val pointCategoryIds = point.recyclePoint.categories.map { it.id }
        val pointCategoriesValid = pointCategoryIds.containsAll(categoryIds)
        val event = MapScreenEvent.UpdatePoint(
            point = point,
            pointCategoriesValid = pointCategoriesValid,
        )
        state.handleEvent(event)
    }

    private fun handleEvent(
        currentState: MapScreenState,
        event: MapScreenEvent,
    ): MapScreenState {
        when (event) {
            is MapScreenEvent.ClusterPointsLoaded -> {
                return currentState.copy(recyclePoints = event.recyclePoints)
            }

            is MapScreenEvent.ChoosePoint -> {
                validatePointCategories(event.point)
            }

            MapScreenEvent.ClearPoint -> {
                return currentState.copy(chosenPoint = null)
            }

            is MapScreenEvent.CleanBucket -> {
                clearBucket()
            }

            is MapScreenEvent.UpdatePoint -> {
                return currentState.copy(
                    chosenPoint = event.point,
                    pointCategoriesValid = event.pointCategoriesValid,
                )
            }
        }
        return currentState
    }
}
