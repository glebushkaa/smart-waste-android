package ua.glebm.smartwaste.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.core.android.extensions.clickableWithoutRipple
import ua.glebm.smartwaste.model.BucketItem
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

@Composable
fun BucketItemsDropdown(
    modifier: Modifier = Modifier,
    selectedItemModifier: Modifier = Modifier,
    selectedItem: BucketItem?,
    bucketItems: List<BucketItem>,
    expanded: Boolean,
    onClick: () -> Unit,
    selectItem: (BucketItem) -> Unit = {},
) {
    val iconDegree by animateFloatAsState(
        if (expanded) EXPANDED_ARROW_DEGREE else DEFAULT_ARROW_DEGREE,
        label = "",
    )

    Surface(
        modifier = modifier,
        shadowElevation = SWTheme.elevation.medium,
        shape = SWTheme.shape.medium,
        contentColor = SWTheme.palette.background,
        color = SWTheme.palette.background,
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(),
            exit = shrinkVertically(),
        ) {
            LazyColumn(
                modifier = Modifier.padding(top = SWTheme.offset.gigantic),
                verticalArrangement = Arrangement.spacedBy(
                    SWTheme.offset.small,
                ),
            ) {
                items(
                    items = bucketItems,
                    key = { material -> material.id },
                ) { item ->
                    if (item.id != selectedItem?.id) {
                        BucketDropdownItem(
                            item = item,
                            onClick = selectItem,
                        )
                    }
                }
            }
        }
        Box(
            modifier = selectedItemModifier
                .fillMaxWidth()
                .height(60.dp)
                .background(SWTheme.palette.background)
                .clickableWithoutRipple(onClick = onClick),
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        start = SWTheme.offset.medium,
                        end = SWTheme.offset.huge,
                    )
                    .fillMaxWidth(),
                text = selectedItem?.name ?: "",
                style = SWTheme.typography.bodyLarge,
                color = SWTheme.palette.onSurface,
            )
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = SWTheme.offset.regular)
                    .rotate(iconDegree),
                painter = painterResource(id = R.drawable.ic_drop_down_arrow),
                contentDescription = null,
                tint = SWTheme.palette.onSurface,
            )
        }
    }
}

@Composable
private fun BucketDropdownItem(
    item: BucketItem,
    onClick: (BucketItem) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(SWTheme.palette.background)
            .clickableWithoutRipple { onClick(item) },
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = SWTheme.offset.medium),
            text = item.name,
            style = SWTheme.typography.bodyLarge,
            color = SWTheme.palette.onBackground,
        )
    }
}

private const val EXPANDED_ARROW_DEGREE = 180f
private const val DEFAULT_ARROW_DEGREE = 0f
