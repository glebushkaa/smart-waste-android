package ua.glebm.smartwaste.ui.screen.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.ui.theme.SWTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean = false,
    supportingText: String = "",
    onValueChanged: (String) -> Unit = {},
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    var textHidden by rememberSaveable {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(
                min = dimensionResource(R.dimen.min_login_text_field_height)
            ),
        value = value,
        onValueChange = {
            val query = if (it.length > MAX_QUERY_LENGTH) {
                it.substring(0, MAX_QUERY_LENGTH)
            } else {
                it
            }
            onValueChanged(query)
        },
        supportingText = {
            Text(
                text = supportingText,
                style = SWTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = SWTheme.palette.error,
            )
        },
        isError = isError,
        visualTransformation = if (
            keyboardType != KeyboardType.Password ||
            textHidden
        ) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        ),
        placeholder = {
            Text(
                text = placeholder,
                style = SWTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = SWTheme.palette.onSurface.copy(
                    alpha = PLACEHOLDER_TEXT_ALPHA,
                ),
            )
        },
        trailingIcon = {
            if (keyboardType != KeyboardType.Password) return@OutlinedTextField
            val imageResId = if (textHidden) {
                R.drawable.ic_visibility_off
            } else {
                R.drawable.ic_visibility
            }

            IconButton(onClick = { textHidden = !textHidden }) {
                Icon(
                    painter = painterResource(imageResId),
                    contentDescription = null,
                    tint = SWTheme.palette.onSurface,
                )
            }
        },
        textStyle = SWTheme.typography.bodyLargeBold,
        colors = TextFieldDefaults.colors(
            cursorColor = SWTheme.palette.onSurface,
            focusedTextColor = SWTheme.palette.onSurface,
            unfocusedTextColor = SWTheme.palette.onSurface,
            errorTextColor = SWTheme.palette.onSurface,
            focusedContainerColor = SWTheme.palette.surface,
            unfocusedContainerColor = SWTheme.palette.surface,
            focusedIndicatorColor = SWTheme.palette.primary,
            errorIndicatorColor = SWTheme.palette.error,
            errorContainerColor = SWTheme.palette.error.copy(alpha = ERROR_CONTAINER_COLOR_ALPHA),
        ),
        shape = SWTheme.shape.huge,
    )
}

private const val MAX_QUERY_LENGTH = 100

private const val PLACEHOLDER_TEXT_ALPHA = 0.6f
private const val ERROR_CONTAINER_COLOR_ALPHA = 0.1f
