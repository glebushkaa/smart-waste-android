package ua.glebm.smartwaste.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.ui.theme.GuideBookTheme
import ua.glebm.smartwaste.ui.theme.GuideTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

@Preview
@Composable
fun BackButtonPreview() {
    GuideBookTheme(darkTheme = true) {
        BackButton(onClick = {})
    }
}

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    tint: Color = GuideTheme.palette.onBackground,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier.rotate(ICON_ROTATION_ANGLE),
            painter = painterResource(id = R.drawable.ic_arrow_next),
            contentDescription = null,
            tint = tint,
        )
    }
}

private const val ICON_ROTATION_ANGLE = 180f
