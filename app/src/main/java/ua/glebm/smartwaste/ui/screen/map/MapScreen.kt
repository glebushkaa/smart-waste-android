@file:OptIn(MapsComposeExperimentalApi::class)

package ua.glebm.smartwaste.ui.screen.map

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import ua.glebm.smartwaste.R

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

@Composable
fun MapScreen(
    state: MapScreenState,
    sendEvent: (MapScreenEvent) -> Unit,
) {
    var granted by rememberSaveable { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted = it },
    )
    val properties by remember(key1 = granted) {
        mutableStateOf(
            MapProperties(isMyLocationEnabled = granted),
        )
    }

    if (state.chosenPoint != null) {
        MapBottomSheet(
            recyclerClusterItem = state.chosenPoint,
            dismissRequest = { sendEvent(MapScreenEvent.ClearPoint) },
            cleanBucketClicked = {
                val event = MapScreenEvent.CleanBucket(state.chosenPoint)
                sendEvent(event)
            },
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = properties,
    ) {
        Clustering(
            items = state.recyclePoints,
            clusterItemContent = {
                Image(
                    painter = painterResource(R.drawable.img_recycle_marker),
                    modifier = Modifier.size(30.dp),
                    contentDescription = null,
                )
            },
            onClusterItemClick = {
                sendEvent(MapScreenEvent.ChoosePoint(it))
                true
            },
        )
    }

    LaunchedEffect(key1 = Unit) {
        launcher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
}
