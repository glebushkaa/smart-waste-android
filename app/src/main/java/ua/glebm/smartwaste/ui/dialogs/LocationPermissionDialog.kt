package ua.glebm.smartwaste.ui.dialogs

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ua.glebm.smartwaste.core.android.extensions.toast
import ua.glebm.smartwaste.ui.components.SWButton
import ua.glebm.smartwaste.ui.components.SWOutlinedButton
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/17/2023
 */

@Composable
fun LocationPermissionDialog(
    onDismissRequest: () -> Unit,
) {
    val openSettingsResultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { _ ->
        onDismissRequest()
    }
    val packageName = LocalContext.current.packageName

    Dialog(onDismissRequest = onDismissRequest) {
        LocationPermissionDialogContent(
            onDismissRequest = onDismissRequest,
            onOpenSettingsClicked = {
                openSettingsResultLauncher.launch(
                    Intent(
                        android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:$packageName"),
                    ),
                )
            },
        )
    }
}

@Composable
private fun LocationPermissionDialogContent(
    onDismissRequest: () -> Unit,
    onOpenSettingsClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SWTheme.palette.surface,
                shape = SWTheme.shape.medium,
            )
            .padding(
                horizontal = SWTheme.offset.large,
            ),
    ) {
        Text(
            text = "Location permission",
            modifier = Modifier.padding(
                top = SWTheme.offset.large,
            ),
            style = SWTheme.typography.titleLarge,
            color = SWTheme.palette.onSurface,
        )
        Text(
            text = "We need permission to validate clear bucket. Open settings to let us know your location ",
            modifier = Modifier.padding(
                top = SWTheme.offset.small,
            ),
            style = SWTheme.typography.titleMedium,
            color = SWTheme.palette.onSurface.copy(
                alpha = 0.6f,
            ),
        )
        SWOutlinedButton(
            modifier = Modifier.padding(
                top = SWTheme.offset.regular,
            )
                .height(50.dp)
                .fillMaxWidth(),
            text = "Decline",
        ) {
            onDismissRequest()
        }
        SWButton(
            modifier = Modifier.padding(
                top = SWTheme.offset.small,
                bottom = SWTheme.offset.large,
            )
                .height(50.dp)
                .fillMaxWidth(),
            text = "Open",
            onClick = onOpenSettingsClicked,
        )
    }
}


