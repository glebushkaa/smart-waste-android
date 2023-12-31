package ua.glebm.smartwaste.domain.usecase.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.glebm.smartwaste.domain.repository.AuthRepository
import ua.glebm.smartwaste.domain.usecase.core.ResultSuspendNoneParamsUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import ua.glebm.smartwaste.model.User
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/28/2023
 */

class GetUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler,
) : ResultSuspendNoneParamsUseCase<User>(
    sessionStatusHandler = sessionStatusHandler,
    useCaseLogger = useCaseLogger,
) {

    override suspend fun invoke() = runCatching {
        withContext(Dispatchers.IO) { authRepository.getUser() }
    }
}
