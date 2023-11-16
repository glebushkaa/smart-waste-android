@file:OptIn(ExperimentalFoundationApi::class)

package ua.glebm.smartwaste.ui.screen.bucket

import android.Manifest
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.core.content.FileProvider
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.core.android.extensions.checkPermission
import ua.glebm.smartwaste.core.android.extensions.toast
import ua.glebm.smartwaste.ui.components.SWButton
import ua.glebm.smartwaste.ui.components.SWOutlinedButton
import ua.glebm.smartwaste.ui.theme.SWTheme
import java.io.File
import java.util.UUID

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@Composable
fun BucketScreen(
    state: BucketState,
    sideEffect: BucketSideEffect? = null,
    sendEvent: (BucketEvent) -> Unit = {},
) {
    if (state.loaderVisible) {
        Popup(
            properties = PopupProperties(focusable = true),
            onDismissRequest = {},
        ) {
            val infiniteTransition = rememberInfiniteTransition(label = "")
            val angle by infiniteTransition.animateFloat(
                initialValue = 0F,
                targetValue = 360F,
                animationSpec = infiniteRepeatable(
                    tween(1000),
                    RepeatMode.Restart,
                ),
                label = "",
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.Black.copy(alpha = 0.7f),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    modifier = Modifier.rotate(angle),
                    painter = painterResource(id = R.drawable.img_recycle),
                    contentDescription = null,
                )
            }
        }
    }

    val context = LocalContext.current
    var uri by remember { mutableStateOf<Uri?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
    ) { pair ->
        if (pair) {
            sendEvent(BucketEvent.ScanImageUri(uri))
            return@rememberLauncherForActivityResult
        }
        context.toast("Image was not saved")
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (!isGranted) {
            context.toast("Camera permission is required to use this feature")
            return@rememberLauncherForActivityResult
        }
        uri = context.getTmpFileUri()
        cameraLauncher.launch(uri)
    }

    if (state.addItemBottomSheetShown) {
        AddItemBottomSheet(
            bucketItems = state.allAvailableItems,
            dismissRequest = { sendEvent(BucketEvent.HideAddItemSheet) },
            scanClicked = {
                if (context.checkPermission(Manifest.permission.CAMERA)) {
                    uri = context.getTmpFileUri()
                    cameraLauncher.launch(uri)
                } else {
                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            },
            addClicked = { sendEvent(BucketEvent.AddBucketItem) },
            increaseCountClicked = { sendEvent(BucketEvent.IncreaseNewBucketItemCount) },
            decreaseCountClicked = { sendEvent(BucketEvent.DecreaseNewBucketItemCount) },
            count = state.newBucketItemCount,
            itemSelected = {
                val event = BucketEvent.SendBucketItem(it)
                sendEvent(event)
            },
            selectedItem = state.bottomSheetBucketItem,
        )
    }

    BucketScreenContent(
        state = state,
        sendEvent = sendEvent,
    )

    LaunchedEffect(key1 = sideEffect) {
        if (sideEffect !is BucketSideEffect.ShowToast) return@LaunchedEffect
        context.toast(sideEffect.message)
    }
}

@Composable
private fun BucketScreenContent(
    state: BucketState,
    sendEvent: (BucketEvent) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SWTheme.palette.background),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = SWTheme.offset.huge)
                .weight(1f)
                .fillMaxWidth()
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(
                space = SWTheme.offset.regular,
            ),
        ) {
            item {
                Text(
                    modifier = Modifier.padding(top = SWTheme.offset.huge),
                    text = "Bucket",
                    style = SWTheme.typography.headlineMedium,
                    color = SWTheme.palette.onBackground,
                )
            }
            items(
                items = state.items,
                key = { item -> item.name },
            ) { bucketItem ->
                BucketListItem(
                    modifier = Modifier.animateItemPlacement(),
                    item = bucketItem,
                    decreaseCountClicked = {
                        val event = BucketEvent.DecreaseCountClicked(name = bucketItem.name)
                        sendEvent(event)
                    },
                    increaseCountClicked = {
                        val event = BucketEvent.IncreaseCountClicked(name = bucketItem.name)
                        sendEvent(event)
                    },
                )
            }
        }
        SWOutlinedButton(
            modifier = Modifier
                .padding(
                    vertical = SWTheme.offset.regular,
                    horizontal = SWTheme.offset.huge,
                )
                .height(60.dp)
                .fillMaxWidth(),
            border = BorderStroke(
                width = 2.dp,
                color = SWTheme.palette.primary,
            ),
            shape = SWTheme.shape.medium,
            text = "Add item",
            textStyle = SWTheme.typography.bodyLarge,
            onClick = {
                sendEvent(BucketEvent.ShowAddItemSheet)
            },
        )
        SWButton(
            modifier = Modifier
                .padding(
                    bottom = SWTheme.offset.huge,
                    start = SWTheme.offset.huge,
                    end = SWTheme.offset.huge,
                )
                .height(60.dp)
                .fillMaxWidth(),
            text = "Find the nearest recycle point",
            textStyle = SWTheme.typography.bodyLarge,
            enabled = state.items.isNotEmpty(),
        ) {
            sendEvent(BucketEvent.OpenMapClicked)
        }
    }
}

private fun Context.getTmpFileUri(): Uri {
    val uuid = UUID.randomUUID().toString()
    val tmpFile = File(filesDir, "$uuid.jpg")

    return FileProvider.getUriForFile(
        applicationContext,
        "${applicationContext.packageName}.provider",
        tmpFile,
    )
}
