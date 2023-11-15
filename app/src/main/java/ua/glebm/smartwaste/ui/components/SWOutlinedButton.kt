package ua.glebm.smartwaste.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@Preview
@Composable
fun SWOutlinedButtonPreview() {
    SWTheme(darkTheme = true) {
        SWOutlinedButton(
            modifier = Modifier,
            onClick = {},
        )
    }
}

@Composable
fun SWOutlinedButton(
    modifier: Modifier = Modifier,
    border: BorderStroke = BorderStroke(
        width = 2.dp,
        color = SWTheme.palette.onSurface.copy(alpha = 0.5f),
    ),
    shape: RoundedCornerShape = SWTheme.shape.small,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(
        SWTheme.offset.default,
    ),
    text: String = "",
    textStyle: TextStyle = SWTheme.typography.bodyMedium,
    content: @Composable RowScope.() -> Unit = {
        Text(
            text = text,
            color = SWTheme.palette.primary,
            style = textStyle,
        )
    },
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = border,
        shape = shape,
        enabled = enabled,
        content = content,
        contentPadding = contentPadding,
    )
}
