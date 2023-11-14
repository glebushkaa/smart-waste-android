package ua.glebm.smartwaste.ui.screen.login

import androidx.compose.runtime.Stable

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@Stable
data class LoginScreenState(
    val loginMode: LoginMode = LoginMode.SIGN_IN,
    val loginButtonEnabled: Boolean = false,
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val emailTextFieldError: String? = null,
    val passwordTextFieldError: String? = null,
    val usernameTextFieldError: String? = null,
)
