@file:OptIn(ExperimentalMaterial3Api::class)

package ua.glebm.smartwaste.ui.screen.map

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/17/2023
 */

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import kotlinx.coroutines.launch
import ua.glebm.smartwaste.core.android.extensions.checkPermission
import ua.glebm.smartwaste.core.android.extensions.toast
import ua.glebm.smartwaste.ui.components.SWButton
import ua.glebm.smartwaste.ui.components.SWOutlinedButton
import ua.glebm.smartwaste.ui.dialogs.LocationPermissionDialog
import ua.glebm.smartwaste.ui.screen.map.model.RecyclerClusterItem
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

@Preview
@Composable
private fun MapBottomSheetPreview() {
    SWTheme(
        darkTheme = true,
    ) {
        MapBottomSheetContent(
            title = "Title",
            address = "Address",
            permissionGranted = true,
        )
    }
}

@Composable
fun MapBottomSheet(
    modifier: Modifier = Modifier,
    dismissRequest: () -> Unit = {},
    recyclerClusterItem: RecyclerClusterItem,
    cleanBucketClicked: () -> Unit = {},
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState()

    var granted by rememberSaveable {
        mutableStateOf(context.checkPermission(ACCESS_FINE_LOCATION))
    }
    var dialogVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            dialogVisible = !it
            granted = it
        },
    )

    if (dialogVisible) {
        LocationPermissionDialog {
            granted = context.checkPermission(ACCESS_FINE_LOCATION)
            dialogVisible = false
        }
    }

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = dismissRequest,
        containerColor = SWTheme.palette.surface,
        sheetState = modalBottomSheetState,
    ) {
        MapBottomSheetContent(
            title = recyclerClusterItem.recyclePoint.name,
            address = recyclerClusterItem.recyclePoint.address,
            permissionGranted = granted,
            giveLocationPermissionClicked = {
                launcher.launch(ACCESS_FINE_LOCATION)
            },
            cleanBucketClicked = {
                scope.launch {
                    cleanBucketClicked()
                    modalBottomSheetState.hide()
                    dismissRequest()
                }
            },
            goClicked = {
                openGoogleMaps(
                    context = context,
                    latitude = recyclerClusterItem.recyclePoint.latitude,
                    longitude = recyclerClusterItem.recyclePoint.longitude,
                )
            },
        )
    }
}

@Composable
private fun MapBottomSheetContent(
    modifier: Modifier = Modifier,
    title: String,
    address: String,
    cleanBucketClicked: () -> Unit = {},
    goClicked: () -> Unit = {},
    giveLocationPermissionClicked: () -> Unit = {},
    permissionGranted: Boolean,
) {
    val annotatedText = buildAnnotatedString {
        val clickableString = "Give a permission"
        val wholeString = "We don't have a location permission. $clickableString"
        val clickSpanStyle = SpanStyle(
            color = SWTheme.palette.link,
            textDecoration = TextDecoration.Underline,
        )
        val defaultSpanStyle = SpanStyle(
            color = SWTheme.palette.onSurface,
        )
        val startIndex = wholeString.indexOf(clickableString)
        val endIndex = startIndex + clickableString.length
        addStyle(
            style = defaultSpanStyle,
            start = 0,
            end = startIndex - 1,
        )
        append(wholeString)
        addStyle(
            style = clickSpanStyle,
            start = startIndex,
            end = endIndex,
        )
        addStringAnnotation(
            tag = "Permission",
            annotation = "permission",
            start = startIndex,
            end = endIndex,
        )
    }

    Column(
        modifier = modifier
            .padding(
                horizontal = SWTheme.offset.large,
            )
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier,
            text = title,
            style = SWTheme.typography.headlineSmall,
            color = SWTheme.palette.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            modifier = Modifier.padding(
                top = SWTheme.offset.tiny,
            ),
            text = address,
            style = SWTheme.typography.titleLarge,
            color = SWTheme.palette.onSurface,
        )

        if (!permissionGranted) {
            ClickableText(
                modifier = Modifier.padding(
                    top = SWTheme.offset.small,
                ),
                text = annotatedText,
                onClick = {
                    annotatedText.getStringAnnotations(
                        tag = "Permission",
                        start = it,
                        end = it,
                    ).firstOrNull()?.let { annotation ->
                        if (annotation.item == "permission") {
                            giveLocationPermissionClicked()
                        }
                    }
                },
                style = SWTheme.typography.bodyMedium,
            )
        }
        SWOutlinedButton(
            modifier = Modifier
                .padding(
                    top = SWTheme.offset.large,
                )
                .height(50.dp)
                .fillMaxWidth(),
            text = "Go",
            textStyle = SWTheme.typography.titleMedium,
            border = BorderStroke(
                width = 1.dp,
                color = SWTheme.palette.primary,
            ),
            shape = SWTheme.shape.medium,
        ) {
            goClicked()
        }

        SWButton(
            modifier = Modifier
                .padding(
                    top = SWTheme.offset.small,
                    bottom = SWTheme.offset.large,
                )
                .height(50.dp)
                .fillMaxWidth(),
            enabled = permissionGranted,
            text = "Clean bucket",
            textStyle = SWTheme.typography.titleMedium,
        ) {
            cleanBucketClicked()
        }
    }
}

fun openGoogleMaps(context: Context, latitude: Double, longitude: Double) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("http://maps.google.com/maps?f=d&daddr=$latitude $longitude"),
    )
    intent.component = ComponentName(
        "com.google.android.apps.maps",
        "com.google.android.maps.MapsActivity",
    )
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        context.toast("Google maps not installed")
    }
}
