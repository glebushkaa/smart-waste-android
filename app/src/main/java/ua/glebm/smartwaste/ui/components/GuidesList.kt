package ua.glebm.smartwaste.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.core.android.extensions.applyIf
import ua.glebm.smartwaste.core.android.extensions.clickableWithoutRipple
import ua.glebm.smartwaste.model.Guide
import ua.glebm.smartwaste.ui.theme.GuideBookTheme
import ua.glebm.smartwaste.ui.theme.GuideTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

@Preview
@Composable
private fun GuideItemPreview() {
    GuideBookTheme {
        GuideItem(
            guide = Guide(
                id = "1",
                emoji = "👋",
                title = "Hello",
                description = "This is a description",
            ),
        )
    }
}

@Composable
fun GuidesList(
    modifier: Modifier = Modifier,
    list: List<Guide> = emptyList(),
    guideClicked: (String) -> Unit = {},
    lazyListState: LazyListState = rememberLazyListState(),
) {
    val topItemShape = GuideTheme.shape.medium.copy(
        bottomEnd = CornerSize(0.dp),
        bottomStart = CornerSize(0.dp),
    )
    val bottomItemShape = GuideTheme.shape.medium.copy(
        topStart = CornerSize(0.dp),
        topEnd = CornerSize(0.dp),
    )

    LazyColumn(
        modifier = modifier.animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(GuideTheme.offset.min),
        state = lazyListState,
    ) {
        items(
            items = list,
            key = { guide -> guide.id },
        ) { guide ->
            GuideItem(
                modifier = Modifier
                    .applyIf(guide.id == list.first().id) {
                        clip(topItemShape)
                    }
                    .applyIf(guide.id == list.last().id) {
                        clip(bottomItemShape)
                    },
                guide = guide,
                guideClicked = guideClicked,
            )
        }
        item {
            Spacer(modifier = Modifier.height(GuideTheme.offset.large))
        }
    }
}

@Composable
private fun GuideItem(
    modifier: Modifier = Modifier,
    guide: Guide,
    guideClicked: (String) -> Unit = {},
) {
    Row(
        modifier = modifier
            .height(
                dimensionResource(R.dimen.guide_item_height)
            )
            .fillMaxWidth()
            .background(GuideTheme.palette.surface)
            .clickableWithoutRipple {
                guideClicked.invoke(guide.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(
                start = GuideTheme.offset.regular
            ),
            text = guide.emoji,
            style = GuideTheme.typography.headlineMedium,
        )
        Column(
            modifier = Modifier
                .padding(
                    top = GuideTheme.offset.large,
                    bottom = GuideTheme.offset.regular,
                    start = GuideTheme.offset.regular,
                    end = GuideTheme.offset.regular
                )
                .fillMaxHeight()
                .weight(1f)
        ) {
            Text(
                text = guide.title,
                maxLines = MAX_TITLE_LINES,
                overflow = TextOverflow.Ellipsis,
                style = GuideTheme.typography.titleMedium,
                color = GuideTheme.palette.onSurface,
            )

            Text(
                modifier = Modifier.padding(top = GuideTheme.offset.tiny),
                text = guide.description,
                style = GuideTheme.typography.bodyMedium,
                color = GuideTheme.palette.onSurface.copy(alpha = DESCRIPTION_TEXT_ALPHA),
                maxLines = MAX_DESCRIPTION_LINES,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Icon(
            modifier = Modifier.padding(end = GuideTheme.offset.regular),
            painter = painterResource(id = R.drawable.ic_arrow_next),
            tint = GuideTheme.palette.onSurface,
            contentDescription = null,
        )
    }
}

private const val MAX_TITLE_LINES = 1
private const val MAX_DESCRIPTION_LINES = 2

private const val DESCRIPTION_TEXT_ALPHA = 0.8f