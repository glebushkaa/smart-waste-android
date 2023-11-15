package ua.glebm.smartwaste.ui.screen.bucket

import ua.glebm.smartwaste.model.BucketItem

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

sealed class BucketEvent {

    data class UpdateBucketItems(
        val items: List<BucketItem>,
    ) : BucketEvent()

    data class IncreaseCountClicked(
        val id: Long,
    ) : BucketEvent()

    data class DecreaseCountClicked(
        val id: Long,
    ) : BucketEvent()
}
