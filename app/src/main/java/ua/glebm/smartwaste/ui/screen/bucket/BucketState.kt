package ua.glebm.smartwaste.ui.screen.bucket

import ua.glebm.smartwaste.model.BucketItem

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

data class BucketState(
    val items: List<BucketItem> = emptyList(),
    val allAvailableItems: List<BucketItem> = emptyList(),
    val bottomSheetBucketItem: BucketItem? = null,
    val newBucketItemCount: Int = 0,
    val addItemBottomSheetShown: Boolean = false,
    val loaderVisible: Boolean = false,
)
