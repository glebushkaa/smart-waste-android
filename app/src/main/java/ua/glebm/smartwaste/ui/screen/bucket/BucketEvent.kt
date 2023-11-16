package ua.glebm.smartwaste.ui.screen.bucket

import android.net.Uri
import ua.glebm.smartwaste.model.BucketItem

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

sealed class BucketEvent {

    data class UpdateBucketItems(
        val items: List<BucketItem>,
    ) : BucketEvent()

    data class IncreaseCountClicked(
        val name: String,
    ) : BucketEvent()

    data class DecreaseCountClicked(
        val name: String,
    ) : BucketEvent()

    data class ScanImageUri(
        val imageUri: Uri? = null,
    ) : BucketEvent()

    data object ShowAddItemSheet : BucketEvent()
    data object HideAddItemSheet : BucketEvent()

    data object AddBucketItem : BucketEvent()
    data object IncreaseNewBucketItemCount : BucketEvent()
    data object DecreaseNewBucketItemCount : BucketEvent()

    data class SendBucketItem(
        val bucketItem: BucketItem,
    ) : BucketEvent()

    data class UpdateAllBucketItems(
        val items: List<BucketItem>,
    ) : BucketEvent()

    data object ClearSelectedBucketItem : BucketEvent()

    data object ShowLoader : BucketEvent()

    data object HideLoader : BucketEvent()
}
