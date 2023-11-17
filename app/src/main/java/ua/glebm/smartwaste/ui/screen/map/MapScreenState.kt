package ua.glebm.smartwaste.ui.screen.map

import androidx.compose.runtime.Stable
import ua.glebm.smartwaste.ui.screen.map.model.RecyclerClusterItem

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@Stable
data class MapScreenState(
    val recyclePoints: List<RecyclerClusterItem> = emptyList(),
    val chosenPoint: RecyclerClusterItem? = null,
    val pointCategoriesValid: Boolean = false,
)
