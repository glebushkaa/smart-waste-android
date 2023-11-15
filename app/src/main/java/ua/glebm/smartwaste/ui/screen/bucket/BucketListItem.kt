package ua.glebm.smartwaste.ui.screen.bucket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.model.BucketItem
import ua.glebm.smartwaste.ui.components.SWOutlinedIconButton
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@Preview
@Composable
fun BucketListItemPreview() {
    SWTheme(darkTheme = true) {
        BucketListItem(
            item = BucketItem(
                id = 0,
                name = "Test",
                count = 10,
            ),
        )
    }
}

@Composable
fun BucketListItem(
    modifier: Modifier = Modifier,
    item: BucketItem,
    decreaseCountClicked: () -> Unit = {},
    increaseCountClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(
                color = SWTheme.palette.surface,
                shape = SWTheme.shape.medium,
            ),
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = SWTheme.offset.large)
                .align(CenterVertically)
                .weight(1f),
            text = item.name,
            style = SWTheme.typography.headlineSmall,
            color = SWTheme.palette.onSurface,
        )
        SWOutlinedIconButton(
            modifier = Modifier
                .padding(end = SWTheme.offset.regular)
                .size(40.dp)
                .align(CenterVertically),
            iconResId = R.drawable.ic_minus,
            onClick = decreaseCountClicked,
            enabled = item.count > 0,
        )
        Text(
            modifier = Modifier.align(CenterVertically)
                .width(32.dp),
            text = item.count.toString(),
            style = SWTheme.typography.headlineSmall,
            color = SWTheme.palette.onSurface,
            textAlign = TextAlign.Center,
        )
        SWOutlinedIconButton(
            modifier = Modifier
                .padding(horizontal = SWTheme.offset.regular)
                .size(40.dp)
                .align(CenterVertically),
            iconResId = R.drawable.ic_plus,
            onClick = increaseCountClicked,
            enabled = item.count < item.limit,
        )
    }
}
