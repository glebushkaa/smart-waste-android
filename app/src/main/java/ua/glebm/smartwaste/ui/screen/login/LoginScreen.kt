package ua.glebm.smartwaste.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import ua.glebm.smartwaste.R
import ua.glebm.smartwaste.core.android.extensions.applyIf
import ua.glebm.smartwaste.ui.screen.login.components.LoginTextField
import ua.glebm.smartwaste.ui.theme.GuideBookTheme
import ua.glebm.smartwaste.ui.theme.GuideTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@Preview
@Composable
private fun LoginScreenPreview() {
    val state = LoginScreenState(
        loginMode = LoginMode.SIGN_UP,
        loginButtonEnabled = true,
    )

    GuideBookTheme(darkTheme = false) {
        LoginScreen(state = state)
    }
}

@Composable
fun LoginScreen(
    state: LoginScreenState = LoginScreenState(),
    sendEvent: (LoginScreenEvent) -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    val titleTextResId = when (state.loginMode) {
        LoginMode.SIGN_IN -> R.string.sign_in
        LoginMode.SIGN_UP -> R.string.sign_up
    }
    val loginText = buildLoginText(loginMode = state.loginMode)

    Column(
        modifier = Modifier
            .background(GuideTheme.palette.background)
            .fillMaxSize()
            .padding(horizontal = GuideTheme.offset.large),
    ) {
        Row(
            modifier = Modifier
                .padding(top = GuideTheme.offset.medium)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = GuideTheme.offset.tiny,
                alignment = CenterHorizontally,
            ),
        ) {
            Image(
                modifier = Modifier.size(
                    dimensionResource(R.dimen.login_book_icon_size),
                ),
                painter = painterResource(id = R.drawable.img_recycle),
                contentDescription = null,
            )
            Text(
                text = stringResource(id = R.string.app_name),
                style = GuideTheme.typography.titleMedium,
                color = GuideTheme.palette.onSurface,
            )
        }
        Text(
            modifier = Modifier.padding(top = GuideTheme.offset.large),
            text = stringResource(titleTextResId),
            style = GuideTheme.typography.headlineLarge,
            color = GuideTheme.palette.onBackground,
        )
        Spacer(modifier = Modifier.height(GuideTheme.offset.huge))
        if (state.loginMode == LoginMode.SIGN_UP) {
            LoginTextField(
                value = state.username,
                placeholder = stringResource(R.string.username),
                isError = state.usernameTextFieldError?.isNotEmpty() == true,
                supportingText = state.usernameTextFieldError ?: "",
                onValueChanged = {
                    val event = LoginScreenEvent.UpdateUsernameTextField(it)
                    sendEvent(event)
                },
            )
        }
        LoginTextField(
            modifier = Modifier.applyIf(state.loginMode == LoginMode.SIGN_UP) {
                padding(top = GuideTheme.offset.medium)
            },
            value = state.email,
            isError = state.emailTextFieldError?.isNotEmpty() == true,
            supportingText = state.emailTextFieldError ?: "",
            placeholder = stringResource(R.string.email),
            onValueChanged = {
                val event = LoginScreenEvent.UpdateEmailTextField(it)
                sendEvent(event)
            },
            keyboardType = KeyboardType.Email,
        )
        LoginTextField(
            modifier = Modifier.padding(top = GuideTheme.offset.medium),
            value = state.password,
            placeholder = stringResource(R.string.password),
            isError = state.passwordTextFieldError?.isNotEmpty() == true,
            supportingText = state.passwordTextFieldError ?: "",
            onValueChanged = {
                val event = LoginScreenEvent.UpdatePasswordTextField(it)
                sendEvent(event)
            },
            keyboardType = KeyboardType.Password,
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    dimensionResource(id = R.dimen.login_button_height),
                ),
            enabled = state.loginButtonEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = GuideTheme.palette.primary,
                disabledContainerColor = GuideTheme.palette.primary.copy(
                    alpha = LOGIN_DISABLED_CONTAINER_COLOR_ALPHA,
                ),
            ),
            shape = GuideTheme.shape.small,
            onClick = {
                focusManager.clearFocus()
                val event = LoginScreenEvent.LoginClicked
                sendEvent(event)
            },
        ) {
            Text(
                text = stringResource(titleTextResId),
                style = GuideTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
                color = GuideTheme.palette.onPrimary,
            )
        }
        ClickableText(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(
                    top = GuideTheme.offset.medium,
                    bottom = GuideTheme.offset.large,
                ),
            text = loginText,
            style = GuideTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
        ) { offset ->
            loginText.getStringAnnotations(
                tag = LOGIN_ANNOTATION_TAG,
                start = offset,
                end = offset,
            ).firstOrNull()?.let {
                val event = LoginScreenEvent.SwapLoginMode
                sendEvent(event)
            }
        }
    }
}

@Composable
private fun buildLoginText(
    loginMode: LoginMode,
): AnnotatedString {
    val startText = when (loginMode) {
        LoginMode.SIGN_IN -> stringResource(R.string.sign_up_question)
        LoginMode.SIGN_UP -> stringResource(R.string.sign_in_question)
    }
    val endText = when (loginMode) {
        LoginMode.SIGN_IN -> stringResource(R.string.sign_up)
        LoginMode.SIGN_UP -> stringResource(R.string.sign_in)
    }
    return buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = GuideTheme.palette.onBackground,
            ),
        ) {
            append(startText)
        }
        pushStringAnnotation(tag = LOGIN_ANNOTATION_TAG, annotation = "")
        withStyle(
            style = SpanStyle(
                color = GuideTheme.palette.primary.copy(
                    alpha = LOGIN_LINK_TEXT_COLOR_ALPHA,
                ),
            ),
        ) {
            append(endText)
        }
        pop()
    }
}

private const val LOGIN_ANNOTATION_TAG = "login"

private const val LOGIN_DISABLED_CONTAINER_COLOR_ALPHA = 0.2f
private const val LOGIN_LINK_TEXT_COLOR_ALPHA = 0.6f
