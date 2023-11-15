package ua.glebm.smartwaste.ui.screen.camera

import android.net.Uri

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

sealed class CameraEvent {

    data class SendImageUri(
        val imageUri: Uri? = null,
    ) : CameraEvent()
}
