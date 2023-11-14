package ua.glebm.smartwaste.domain.usecase.auth

import ua.glebm.smartwaste.domain.datastore.AuthDataStore
import ua.glebm.smartwaste.domain.repository.AuthRepository
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import ua.glebm.smartwaste.domain.usecase.auth.SignInUseCase.Params
import ua.glebm.smartwaste.domain.usecase.core.ResultSuspendUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val authDataStore: AuthDataStore,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler
) : ResultSuspendUseCase<Unit, Params>(
    useCaseLogger = useCaseLogger,
    sessionStatusHandler = sessionStatusHandler
) {

    override suspend fun invoke(params: Params) = runCatching {
        withContext(Dispatchers.IO) {
            val token = authRepository.login(params.email, params.password)
            authDataStore.updateAccessToken(token)
        }
    }

    data class SignInException(
        val field: LoginField? = null,
        override val message: String,
    ) : Throwable()

    data class Params(val email: String, val password: String) : UseCase.Params
}
