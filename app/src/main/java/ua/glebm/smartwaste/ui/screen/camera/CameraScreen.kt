package ua.glebm.smartwaste.ui.screen.camera

import android.Manifest
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import ua.glebm.smartwaste.core.android.extensions.checkPermission
import ua.glebm.smartwaste.core.android.extensions.toast
import ua.glebm.smartwaste.ui.theme.SWTheme
import java.io.File

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@Composable
fun CameraScreen(
    state: CameraState,
    sendEvent: (CameraEvent) -> Unit,
) {
    val context = LocalContext.current
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = TakePictureWithUriReturnContract(),
    ) { pair ->
        if (pair.first) {
            sendEvent(CameraEvent.SendImageUri(pair.second))
        } else {
            context.toast("Image was not saved")
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            val imageUri = context.getTmpFileUri()
            cameraLauncher.launch(imageUri)
        } else {
            context.toast("Camera permission is required to use this feature")
        }
    }

    CameraScreenContent(
        state = state,
    )

    LaunchedEffect(key1 = Unit) {
        if (context.checkPermission(Manifest.permission.CAMERA)) {
            val imageUri = context.getTmpFileUri()
            cameraLauncher.launch(imageUri)
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}

private fun Context.getTmpFileUri(): Uri {
    val tmpFile = File.createTempFile("tmp_image_file", ".png", filesDir).apply {
        createNewFile()
        deleteOnExit()
    }

    return FileProvider.getUriForFile(
        applicationContext,
        "${applicationContext.packageName}.provider",
        tmpFile,
    )
}

@Composable
private fun CameraScreenContent(
    state: CameraState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SWTheme.palette.background),
    ) {
        if (state.imageUri == null) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "There is no image(",
                style = SWTheme.typography.bodyLarge,
                color = SWTheme.palette.onBackground,
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .padding(
                        horizontal = SWTheme.offset.huge,
                        vertical = SWTheme.offset.huge,
                    )
                    .height(400.dp)
                    .fillMaxWidth(),
                model = state.imageUri,
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }
    }
}
