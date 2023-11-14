package ua.glebm.smartwaste.ui.screen.map

import androidx.compose.runtime.Stable
import com.google.maps.android.clustering.ClusterItem
import ua.glebm.smartwaste.model.RecyclePoint

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@Stable
data class MapScreenState(
    val recyclePoints: List<ClusterItem> = emptyList(),
)
