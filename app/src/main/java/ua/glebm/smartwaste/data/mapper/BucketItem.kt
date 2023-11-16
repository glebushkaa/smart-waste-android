package ua.glebm.smartwaste.data.mapper

import ua.glebm.smartwaste.data.database.entity.BucketCategoryEntity
import ua.glebm.smartwaste.data.database.entity.BucketItemEntity
import ua.glebm.smartwaste.data.database.entity.BucketWithCategories
import ua.glebm.smartwaste.data.network.dto.bucket.BucketItemDto
import ua.glebm.smartwaste.model.BucketItem

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

fun BucketItem.toBucketEntity(): BucketItemEntity {
    return BucketItemEntity(
        name = name,
        count = count,
        id = id,
    )
}

fun BucketWithCategories.toBucketItem(): BucketItem {
    return BucketItem(
        name = bucket.name,
        count = bucket.count,
        categories = categories.map { it.toCategory() },
        id = bucket.id,
    )
}

fun BucketItemDto.toBucketItem(): BucketItem {
    return BucketItem(
        name = name ?: "",
        categories = categories.map { it.toCategory() },
        id = id ?: 0,
    )
}

fun BucketItemDto.CategoryDto.toCategory(): BucketItem.Category {
    return BucketItem.Category(
        name = name ?: "",
        slug = slug ?: "",
        id = id ?: 0,
    )
}

fun BucketCategoryEntity.toCategory(): BucketItem.Category {
    return BucketItem.Category(
        name = name,
        slug = slug,
        id = id,
    )
}

fun BucketItem.Category.toCategoryEntity(): BucketCategoryEntity {
    return BucketCategoryEntity(
        name = name,
        slug = slug,
        id = id,
    )
}
