package ua.glebm.smartwaste.ui.screen.map.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import ua.glebm.smartwaste.model.RecyclePoint

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

data class RecyclerClusterItem(
    val recyclePoint: RecyclePoint,
) : ClusterItem {
    override fun getPosition(): LatLng {
        return LatLng(
            recyclePoint.latitude,
            recyclePoint.longitude,
        )
    }

    override fun getTitle(): String {
        return recyclePoint.name
    }

    override fun getSnippet(): String {
        return recyclePoint.address
    }
}
