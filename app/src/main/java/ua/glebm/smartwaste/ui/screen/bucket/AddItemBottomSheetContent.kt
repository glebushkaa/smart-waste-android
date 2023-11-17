@file:OptIn(ExperimentalMaterial3Api::class)

package ua.glebm.smartwaste.ui.screen.bucket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import kotlinx.coroutines.launch
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.model.BucketItem
import ua.glebm.smartwaste.ui.components.BucketItemsDropdown
import ua.glebm.smartwaste.ui.components.SWButton
import ua.glebm.smartwaste.ui.components.SWIconButton
import ua.glebm.smartwaste.ui.components.SWOutlinedButton
import ua.glebm.smartwaste.ui.components.SWOutlinedIconButton
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

@Composable
fun AddItemBottomSheet(
    modifier: Modifier = Modifier,
    bucketItems: List<BucketItem> = emptyList(),
    selectedItem: BucketItem?,
    count: Int = 0,
    countLimit: Int = 10,
    dismissRequest: () -> Unit = {},
    itemSelected: (BucketItem) -> Unit = {},
    scanClicked: () -> Unit = {},
    addClicked: () -> Unit = {},
    increaseCountClicked: () -> Unit = {},
    decreaseCountClicked: () -> Unit = {},
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = dismissRequest,
        containerColor = SWTheme.palette.surface,
        sheetState = modalBottomSheetState,
    ) {
        AddItemBottomSheetContent(
            bucketItems = bucketItems,
            countLimit = countLimit,
            scanClicked = scanClicked,
            cancelClicked = {
                scope.launch {
                    modalBottomSheetState.hide()
                    dismissRequest()
                }
            },
            addClicked = {
                scope.launch {
                    addClicked()
                    modalBottomSheetState.hide()
                    dismissRequest()
                }
            },
            increaseCountClicked = increaseCountClicked,
            decreaseCountClicked = decreaseCountClicked,
            itemSelected = itemSelected,
            selectedItem = selectedItem,
            count = count,
        )
    }
}

@Composable
private fun AddItemBottomSheetContent(
    modifier: Modifier = Modifier,
    bucketItems: List<BucketItem> = emptyList(),
    selectedItem: BucketItem?,
    count: Int = 0,
    countLimit: Int = 10,
    itemSelected: (BucketItem) -> Unit = {},
    scanClicked: () -> Unit = {},
    cancelClicked: () -> Unit = {},
    addClicked: () -> Unit = {},
    increaseCountClicked: () -> Unit = {},
    decreaseCountClicked: () -> Unit = {},
) {
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }
    ConstraintLayout(
        modifier = modifier.fillMaxWidth(),
        constraintSet = addItemBottomSheetConstraints(),
    ) {
        Text(
            modifier = Modifier.layoutId(TITLE),
            text = "Add item",
            style = SWTheme.typography.headlineSmall,
            color = SWTheme.palette.onSurface,
        )
        SWIconButton(
            modifier = Modifier
                .height(60.dp)
                .layoutId(SCAN_BUTTON),
            iconResId = R.drawable.ic_scan_item,
            tint = SWTheme.palette.onSurface,
            onClick = scanClicked,
        )
        SWOutlinedIconButton(
            modifier = Modifier.size(40.dp).layoutId(MINUS_BUTTON),
            iconResId = R.drawable.ic_minus,
            onClick = decreaseCountClicked,
            enabled = count > 0,
            iconTint = SWTheme.palette.onBackground,
        )
        Text(
            modifier = Modifier.width(40.dp).layoutId(COUNTER_TEXT),
            text = count.toString(),
            style = SWTheme.typography.headlineMedium,
            color = SWTheme.palette.onBackground,
            textAlign = TextAlign.Center,
        )
        SWOutlinedIconButton(
            modifier = Modifier.size(40.dp).layoutId(PLUS_BUTTON),
            iconResId = R.drawable.ic_plus,
            onClick = increaseCountClicked,
            enabled = count < countLimit,
            iconTint = SWTheme.palette.onBackground,
        )
        SWOutlinedButton(
            modifier = Modifier
                .height(60.dp)
                .layoutId(ADD_BUTTON),
            border = BorderStroke(
                width = 1.dp,
                color = SWTheme.palette.primary,
            ),
            shape = SWTheme.shape.medium,
            text = "Add",
            textStyle = SWTheme.typography.bodyLarge,
            onClick = addClicked,
        )
        SWButton(
            modifier = Modifier
                .height(60.dp)
                .layoutId(CANCEL_BUTTON),
            text = "Cancel",
            textStyle = SWTheme.typography.bodyLarge,
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = SWTheme.palette.error,
                contentColor = SWTheme.palette.onError,
            ),
            onClick = cancelClicked,
        )
        Spacer(
            modifier = Modifier
                .height(SWTheme.offset.huge)
                .layoutId(BOTTOM_SPACER),
        )
        BucketItemsDropdown(
            modifier = Modifier
                .heightIn(max = 300.dp)
                .layoutId(MATERIAL_DROPDOWN),
            selectedItemModifier = Modifier.layoutId(MATERIAL_DROPDOWN_SELECTED_ITEM),
            selectedItem = selectedItem,
            bucketItems = bucketItems,
            expanded = dropdownExpanded,
            onClick = { dropdownExpanded = !dropdownExpanded },
            selectItem = {
                itemSelected(it)
                dropdownExpanded = false
            },
        )
    }
}

@Composable
private fun addItemBottomSheetConstraints(): ConstraintSet {
    val mediumOffset = SWTheme.offset.medium
    val regularOffset = SWTheme.offset.regular
    val largeOffset = SWTheme.offset.large
    val hugeOffset = SWTheme.offset.huge
    val giganticOffset = SWTheme.offset.gigantic
    return ConstraintSet {
        val title = createRefFor(TITLE)
        val materialDropdown = createRefFor(MATERIAL_DROPDOWN)
        val materialDropdownSelectedItem = createRefFor(MATERIAL_DROPDOWN_SELECTED_ITEM)
        val scanButton = createRefFor(SCAN_BUTTON)

        val plusButton = createRefFor(PLUS_BUTTON)
        val minusButton = createRefFor(MINUS_BUTTON)
        val counterText = createRefFor(COUNTER_TEXT)

        val cancelButton = createRefFor(CANCEL_BUTTON)
        val addButton = createRefFor(ADD_BUTTON)
        val bottomSpacer = createRefFor(BOTTOM_SPACER)

        constrain(title) {
            top.linkTo(anchor = parent.top)
            start.linkTo(anchor = parent.start, margin = hugeOffset)
        }

        constrain(materialDropdown) {
            top.linkTo(anchor = title.bottom, margin = regularOffset)
            start.linkTo(anchor = parent.start, margin = hugeOffset)
            end.linkTo(anchor = scanButton.start, margin = largeOffset)
            width = Dimension.fillToConstraints
        }

        constrain(materialDropdownSelectedItem) {
            top.linkTo(anchor = materialDropdown.top)
            start.linkTo(anchor = materialDropdown.start)
            end.linkTo(anchor = materialDropdown.end)
            width = Dimension.fillToConstraints
        }

        constrain(scanButton) {
            top.linkTo(anchor = materialDropdown.top)
            end.linkTo(anchor = parent.end, margin = largeOffset)
        }

        constrain(plusButton) {
            top.linkTo(anchor = materialDropdownSelectedItem.bottom, margin = largeOffset)
            start.linkTo(anchor = counterText.end, margin = regularOffset)
        }

        constrain(counterText) {
            top.linkTo(anchor = minusButton.top)
            start.linkTo(anchor = minusButton.end, margin = regularOffset)
            height = Dimension.fillToConstraints
        }

        constrain(minusButton) {
            top.linkTo(anchor = materialDropdownSelectedItem.bottom, margin = largeOffset)
            start.linkTo(anchor = parent.start, margin = hugeOffset)
        }

        constrain(addButton) {
            top.linkTo(anchor = plusButton.bottom, margin = giganticOffset)
            start.linkTo(anchor = parent.start, margin = hugeOffset)
            end.linkTo(anchor = parent.end, margin = largeOffset)
            width = Dimension.fillToConstraints
        }

        constrain(cancelButton) {
            top.linkTo(anchor = addButton.bottom, margin = mediumOffset)
            start.linkTo(anchor = parent.start, margin = hugeOffset)
            end.linkTo(anchor = parent.end, margin = largeOffset)
            width = Dimension.fillToConstraints
        }
        constrain(bottomSpacer) {
            top.linkTo(anchor = cancelButton.bottom, margin = regularOffset)
            bottom.linkTo(anchor = parent.bottom)
            width = Dimension.fillToConstraints
        }
    }
}

private const val TITLE = "title"
private const val MATERIAL_DROPDOWN = "material_dropdown"
private const val MATERIAL_DROPDOWN_SELECTED_ITEM = "material_dropdown_selected_item"
private const val SCAN_BUTTON = "scan_button"
private const val PLUS_BUTTON = "plus_button"
private const val MINUS_BUTTON = "minus_button"
private const val COUNTER_TEXT = "counter_text"
private const val CANCEL_BUTTON = "cancel_button"
private const val ADD_BUTTON = "add_button"
private const val BOTTOM_SPACER = "bottom_spacer"
