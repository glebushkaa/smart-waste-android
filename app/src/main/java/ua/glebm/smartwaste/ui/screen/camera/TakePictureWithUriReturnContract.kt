package ua.glebm.smartwaste.ui.screen.camera

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.CallSuper

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

class TakePictureWithUriReturnContract : ActivityResultContract<Uri, Pair<Boolean, Uri?>>() {

    private var imageUri: Uri? = null

    @CallSuper
    override fun createIntent(context: Context, input: Uri): Intent {
        imageUri = input
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, input)
    }

    override fun getSynchronousResult(
        context: Context,
        input: Uri,
    ): SynchronousResult<Pair<Boolean, Uri?>>? = null

    @Suppress("AutoBoxing")
    override fun parseResult(resultCode: Int, intent: Intent?): Pair<Boolean, Uri?> {
        return (resultCode == Activity.RESULT_OK) to imageUri
    }
}
