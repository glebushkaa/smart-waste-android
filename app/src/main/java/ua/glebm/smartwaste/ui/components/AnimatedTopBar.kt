package ua.glebm.smartwaste.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.core.common.FIVE_HUNDRED_MILLIS
import ua.glebm.smartwaste.ui.theme.GuideTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/1/2023.
 */

@Composable
fun AnimatedTopBar(
    modifier: Modifier = Modifier,
    visible: Boolean,
    closeVisible: Boolean = false,
    closeClicked: () -> Unit = {},
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(FIVE_HUNDRED_MILLIS.toInt()),
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(FIVE_HUNDRED_MILLIS.toInt()),
        ),
    ) {
        GuideTopBar(
            closeVisible = closeVisible,
            closeClicked = closeClicked,
        )
    }
}

@Composable
@Preview
private fun GuideTopBar(
    modifier: Modifier = Modifier,
    closeClicked: () -> Unit = {},
    closeVisible: Boolean = false,
) {
    Row(
        modifier = modifier
            .height(
                dimensionResource(R.dimen.top_bar_height),
            )
            .fillMaxWidth()
            .background(GuideTheme.palette.surface),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(start = GuideTheme.offset.regular)
                .size(
                    dimensionResource(R.dimen.top_bar_image_size),
                ),
            painter = painterResource(id = R.drawable.img_recycle),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .padding(
                    horizontal = GuideTheme.offset.medium,
                )
                .weight(1f),
            text = stringResource(R.string.app_name),
            style = GuideTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
            ),
            color = GuideTheme.palette.onBackground,
        )
        AnimatedVisibility(
            modifier = Modifier.padding(end = GuideTheme.offset.regular),
            visible = closeVisible,
        ) {
            GuideIconButton(
                iconResId = R.drawable.ic_close,
                tint = GuideTheme.palette.onBackground,
                onClick = closeClicked,
            )
        }
    }
}
