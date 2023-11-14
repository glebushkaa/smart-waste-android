package ua.glebm.smartwaste.ui.navigation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.core.android.extensions.clickableWithoutRipple
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/30/2023
 */

@Composable
fun GuideBottomNavItem(
    modifier: Modifier = Modifier,
    iconResId: Int,
    text: String,
    selected: Boolean = false,
    onClick: () -> Unit = {},
) {
    val color = if (selected) {
        SWTheme.palette.primary
    } else {
        SWTheme.palette.onBackground.copy(alpha = UNSELECTED_NAV_ITEM_ALPHA)
    }
    val animatedColor by animateColorAsState(targetValue = color, label = "")

    Column(
        modifier = modifier
            .size(
                dimensionResource(R.dimen.bottom_nav_item_size),
            )
            .clickableWithoutRipple(onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(
                dimensionResource(R.dimen.bottom_nav_item_icon_size),
            ),
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = animatedColor,
        )
        Text(
            text = text,
            style = SWTheme.typography.bodySmall,
            color = animatedColor,
        )
    }
}

private const val UNSELECTED_NAV_ITEM_ALPHA = 0.4f
