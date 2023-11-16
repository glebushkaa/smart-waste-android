package ua.glebm.smartwaste.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@Preview
@Composable
fun SWButtonPreview() {
    SWButton(
        text = "Button",
        onClick = {},
    )
}

@Composable
fun SWButton(
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = SWTheme.palette.primary,
        contentColor = SWTheme.palette.onPrimary,
        disabledContainerColor = SWTheme.palette.primary.copy(
            alpha = 0.2f,
        ),
    ),
    text: String = "",
    textStyle: TextStyle = SWTheme.typography.bodyMedium,
    content: @Composable RowScope.() -> Unit = {
        Text(
            text = text,
            color = SWTheme.palette.onPrimary,
            style = textStyle,
        )
    },
    shape: RoundedCornerShape = SWTheme.shape.medium,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = buttonColors,
        content = content,
        shape = shape,
        enabled = enabled,
    )
}
