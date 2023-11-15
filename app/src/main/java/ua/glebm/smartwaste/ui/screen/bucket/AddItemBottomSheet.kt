package ua.glebm.smartwaste.ui.screen.bucket

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import ua.glebm.smartwaste.model.Material
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

@Preview
@Composable
fun AddItemBottomSheetPreview() {
    BucketScreen(
        state = BucketState(),
    )
}

@Composable
fun AddItemBottomSheet(
    materials: List<Material> = emptyList(),
    countLimit: Int = 10,
) {
    var count by rememberSaveable { mutableIntStateOf(0) }
    ConstraintLayout(
        constraintSet = addItemBottomSheetConstraints(),
    ) {
        Text(
            modifier = Modifier.layoutId(TITLE),
            text = "Add item",
            style = SWTheme.typography.headlineSmall,
            color = SWTheme.palette.onSurface,
        )
    }
}

@Composable
private fun addItemBottomSheetConstraints(): ConstraintSet {
    val largeOffset = SWTheme.offset.large
    return ConstraintSet {
        val title = createRefFor(TITLE)
        constrain(title) {
            top.linkTo(anchor = parent.top, margin = largeOffset)
            start.linkTo(anchor = parent.start, margin = largeOffset)
        }
    }
}

private const val TITLE = "title"
