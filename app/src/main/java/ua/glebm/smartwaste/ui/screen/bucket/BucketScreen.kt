package ua.glebm.smartwaste.ui.screen.bucket

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.glebm.smartwaste.model.BucketItem
import ua.glebm.smartwaste.ui.components.SWButton
import ua.glebm.smartwaste.ui.components.SWOutlinedButton
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@Preview
@Composable
fun BucketScreenPreview() {
    val list = listOf(
        BucketItem(
            id = 1,
            name = "Item 1",
            count = 1,
        ),
        BucketItem(
            id = 2,
            name = "Item 2",
            count = 2,
        ),
        BucketItem(
            id = 3,
            name = "Item 3",
            count = 3,
        ),
    )

    SWTheme(darkTheme = true) {
        BucketScreen(
            state = BucketState(
                items = list,
            ),
        )
    }
}

@Composable
fun BucketScreen(
    state: BucketState,
    sendEvent: (BucketEvent) -> Unit = {},
) {
    BucketScreenContent(
        state = state,
        sendEvent = sendEvent,
    )
}

@Composable
private fun BucketScreenContent(
    state: BucketState,
    sendEvent: (BucketEvent) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SWTheme.palette.background),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = SWTheme.offset.huge)
                .weight(1f)
                .fillMaxWidth()
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(
                space = SWTheme.offset.regular,
            ),
        ) {
            item {
                Text(
                    modifier = Modifier.padding(top = SWTheme.offset.huge),
                    text = "Bucket",
                    style = SWTheme.typography.headlineMedium,
                    color = SWTheme.palette.onBackground,
                )
            }
            items(
                items = state.items,
                key = { item -> item.id },
            ) { bucketItem ->
                BucketListItem(
                    item = bucketItem,
                    decreaseCountClicked = {
                        val event = BucketEvent.DecreaseCountClicked(id = bucketItem.id)
                        sendEvent(event)
                    },
                    increaseCountClicked = {
                        val event = BucketEvent.IncreaseCountClicked(id = bucketItem.id)
                        sendEvent(event)
                    },
                )
            }
        }
        SWOutlinedButton(
            modifier = Modifier
                .padding(
                    vertical = SWTheme.offset.regular,
                    horizontal = SWTheme.offset.huge,
                )
                .height(60.dp)
                .fillMaxWidth(),
            border = BorderStroke(
                width = 2.dp,
                color = SWTheme.palette.primary,
            ),
            shape = SWTheme.shape.medium,
            text = "Add item",
            textStyle = SWTheme.typography.bodyLarge,
        ) {}
        SWButton(
            modifier = Modifier
                .padding(
                    bottom = SWTheme.offset.huge,
                    start = SWTheme.offset.huge,
                    end = SWTheme.offset.huge,
                )
                .height(60.dp)
                .fillMaxWidth(),
            text = "Find the nearest recycle point",
            textStyle = SWTheme.typography.bodyLarge,
        ) {
        }
    }
}
