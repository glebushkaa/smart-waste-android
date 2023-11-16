package ua.glebm.smartwaste.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@Preview
@Composable
fun SWOutlinedIconButtonPreview() {
    SWTheme(darkTheme = true) {
        SWOutlinedIconButton(
            modifier = Modifier,
            iconModifier = Modifier,
            iconResId = R.drawable.ic_arrow_next,
            iconTint = SWTheme.palette.onBackground,
            onClick = {},
        )
    }
}

@Composable
fun SWOutlinedIconButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    @DrawableRes iconResId: Int = R.drawable.ic_arrow_next,
    iconTint: Color = SWTheme.palette.onSurface,
    border: BorderStroke = BorderStroke(
        width = 1.dp,
        color = SWTheme.palette.onBackground.copy(alpha = 0.5f),
    ),
    contentPadding: PaddingValues = PaddingValues(
        SWTheme.offset.default,
    ),
    shape: RoundedCornerShape = SWTheme.shape.small,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = border,
        contentPadding = contentPadding,
        shape = shape,
        enabled = enabled,
    ) {
        Icon(
            modifier = iconModifier,
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = if (enabled) iconTint else iconTint.copy(alpha = 0.2f),
        )
    }
}
