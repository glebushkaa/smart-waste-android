package ua.glebm.smartwaste.ui.screen.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import ua.glebm.smartwaste.core.android.BaseViewModel
import ua.glebm.smartwaste.core.android.stateReducerFlow
import ua.glebm.smartwaste.domain.usecase.auth.LoginField
import ua.glebm.smartwaste.domain.usecase.auth.SignInUseCase
import ua.glebm.smartwaste.domain.usecase.auth.SignUpUseCase
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
) : BaseViewModel() {

    val state = stateReducerFlow(
        initialState = LoginScreenState(),
        reduceState = ::handleEvent,
    )
    val navigationEffect = Channel<LoginNavigationEffect>()

    private fun swapLoginMode() = when (state.value.loginMode) {
        LoginMode.SIGN_UP -> LoginMode.SIGN_IN
        LoginMode.SIGN_IN -> LoginMode.SIGN_UP
    }

    private fun validateInput(
        email: String = state.value.email,
        username: String = state.value.username,
        password: String = state.value.password,
        loginMode: LoginMode = state.value.loginMode,
    ) = viewModelScope.launch(Dispatchers.IO) {
        val isEmailValid = validateEmail(email)
        val isUsernameValid = validateUsername(username)
        val isPasswordValid = validatePassword(password)
        val signUpUsernameCheck = if (loginMode == LoginMode.SIGN_UP) isUsernameValid else true
        val isInputValid = isEmailValid && isPasswordValid && signUpUsernameCheck
        val event = if (isInputValid) {
            LoginScreenEvent.EnableLoginButton
        } else {
            LoginScreenEvent.DisableLoginButton
        }
        state.handleEvent(event)
    }

    private fun validateEmail(email: String): Boolean {
        val email = email.trim()
        return email.contains("@") && email.length >= MIN_EMAIL_LENGTH
    }

    private fun validateUsername(username: String): Boolean {
        val username = username.trim()
        return username.length >= MIN_USERNAME_LENGTH &&
            !username.contains("[!\"#$%&'()*+,-/:;\\\\<=>?@\\[\\]^`{|}~]".toRegex())
    }

    private fun validatePassword(password: String): Boolean {
        val password = password.trim()
        return password.length >= MIN_PASSWORD_LENGTH
    }

    private fun signIn() = viewModelScope.launch(Dispatchers.IO) {
        val email = state.value.email.trim()
        val password = state.value.password.trim()
        val params = SignInUseCase.Params(email = email, password = password)
        signInUseCase(params).onSuccess {
            val navEffect = LoginNavigationEffect.NavigateToHomeScreen
            navigationEffect.trySend(navEffect)
        }.onFailure {
            val signInException = it as? SignInUseCase.SignInException ?: return@onFailure
            val field = signInException.field ?: return@onFailure
            val event = LoginScreenEvent.SendTextFieldMessage(
                field = field,
                message = signInException.message,
            )
            state.handleEvent(event)
        }
    }

    private fun signUp() = viewModelScope.launch(Dispatchers.IO) {
        val email = state.value.email.trim()
        val username = state.value.username.trim()
        val password = state.value.password.trim()
        val params = SignUpUseCase.Params(email = email, username = username, password = password)
        signUpUseCase(params).onSuccess {
            val navEffect = LoginNavigationEffect.NavigateToHomeScreen
            navigationEffect.trySend(navEffect)
        }.onFailure {
            val signInException = it as? SignUpUseCase.SignUpException ?: return@onFailure
            val field = signInException.field ?: return@onFailure
            val event = LoginScreenEvent.SendTextFieldMessage(
                field = field,
                message = signInException.message,
            )
            state.handleEvent(event)
        }
    }

    private fun handleEvent(
        currentState: LoginScreenState,
        event: LoginScreenEvent,
    ): LoginScreenState {
        when (event) {
            is LoginScreenEvent.SwapLoginMode -> {
                val newMode = swapLoginMode()
                validateInput(loginMode = newMode)
                return currentState.copy(loginMode = newMode)
            }

            is LoginScreenEvent.UpdateEmailTextField -> {
                validateInput(email = event.email)
                return currentState.copy(
                    email = event.email,
                    emailTextFieldError = null,
                )
            }

            is LoginScreenEvent.UpdatePasswordTextField -> {
                validateInput(password = event.password)
                return currentState.copy(
                    password = event.password,
                    passwordTextFieldError = null,
                )
            }

            is LoginScreenEvent.UpdateUsernameTextField -> {
                validateInput(username = event.username)
                return currentState.copy(
                    username = event.username,
                    usernameTextFieldError = null,
                )
            }

            LoginScreenEvent.DisableLoginButton -> {
                return currentState.copy(loginButtonEnabled = false)
            }

            LoginScreenEvent.EnableLoginButton -> {
                return currentState.copy(loginButtonEnabled = true)
            }

            LoginScreenEvent.LoginClicked -> {
                when (currentState.loginMode) {
                    LoginMode.SIGN_IN -> signIn()
                    LoginMode.SIGN_UP -> signUp()
                }
            }

            is LoginScreenEvent.SendTextFieldMessage -> {
                return when (event.field) {
                    LoginField.EMAIL -> currentState.copy(emailTextFieldError = event.message)
                    LoginField.PASSWORD -> currentState.copy(passwordTextFieldError = event.message)
                    LoginField.USERNAME -> currentState.copy(usernameTextFieldError = event.message)
                }
            }
        }
        return currentState
    }

    private companion object {
        const val MIN_EMAIL_LENGTH = 10
        const val MIN_USERNAME_LENGTH = 4
        const val MIN_PASSWORD_LENGTH = 3
    }
}
