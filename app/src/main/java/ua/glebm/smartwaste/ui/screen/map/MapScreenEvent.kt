package ua.glebm.smartwaste.ui.screen.map

import com.google.maps.android.clustering.ClusterItem

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

sealed class MapScreenEvent {

    data class ClusterPointsLoaded(
        val recyclePoints: List<ClusterItem>,
    ) : MapScreenEvent()
}
