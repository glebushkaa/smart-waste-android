package ua.glebm.smartwaste.ui.screen.map

import ua.glebm.smartwaste.ui.screen.map.model.RecyclerClusterItem

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

sealed class MapScreenEvent {

    data class ClusterPointsLoaded(
        val recyclePoints: List<RecyclerClusterItem>,
    ) : MapScreenEvent()

    data class ChoosePoint(
        val point: RecyclerClusterItem,
    ) : MapScreenEvent()

    data object ClearPoint : MapScreenEvent()

    data class CleanBucket(
        val point: RecyclerClusterItem,
    ) : MapScreenEvent()

    data class UpdatePoint(
        val point: RecyclerClusterItem,
        val pointCategoriesValid: Boolean,
    ) : MapScreenEvent()
}
