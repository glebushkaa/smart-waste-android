package ua.glebm.smartwaste.ui.screen.camera

import android.Manifest
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import ua.glebm.smartwaste.core.android.extensions.checkPermission
import ua.glebm.smartwaste.core.android.extensions.toast
import ua.glebm.smartwaste.ui.components.SWButton
import ua.glebm.smartwaste.ui.theme.SWTheme
import java.io.File
import java.util.UUID

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@Composable
fun CameraScreen(
    state: CameraState,
    sendEvent: (CameraEvent) -> Unit,
) {
    val context = LocalContext.current
    var uri by remember { mutableStateOf<Uri?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
    ) { pair ->
        if (pair) {
            sendEvent(CameraEvent.SendImageUri(uri))
        } else {
            context.toast("Image was not saved")
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            uri = context.getTmpFileUri()
            cameraLauncher.launch(uri)
        } else {
            context.toast("Camera permission is required to use this feature")
        }
    }

    CameraScreenContent(
        state = state,
        launchCamera = {
            if (context.checkPermission(Manifest.permission.CAMERA)) {
                uri = context.getTmpFileUri()
                cameraLauncher.launch(uri)
            } else {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        },
    )

    LaunchedEffect(key1 = Unit) {
        if (context.checkPermission(Manifest.permission.CAMERA)) {
            uri = context.getTmpFileUri()
            cameraLauncher.launch(uri)
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}

@Composable
private fun CameraScreenContent(
    state: CameraState,
    launchCamera: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SWTheme.palette.background),
    ) {
        if (state.imageUri == null) {
            Text(
                modifier = Modifier.fillMaxHeight()
                    .align(CenterHorizontally)
                    .padding(horizontal = SWTheme.offset.huge),
                text = "There is no image(",
                style = SWTheme.typography.bodyLarge,
                color = SWTheme.palette.onBackground,
                textAlign = TextAlign.Center,
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .padding(
                        horizontal = SWTheme.offset.huge,
                        vertical = SWTheme.offset.huge,
                    )
                    .height(400.dp)
                    .fillMaxWidth()
                    .clip(SWTheme.shape.medium),
                model = state.imageUri,
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }
        SWButton(
            modifier = Modifier
                .padding(horizontal = SWTheme.offset.huge)
                .height(48.dp)
                .fillMaxWidth(),
            text = "Take a picture",
            onClick = launchCamera,
        )
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
