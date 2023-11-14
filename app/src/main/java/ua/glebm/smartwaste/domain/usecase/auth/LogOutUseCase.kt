package ua.glebm.smartwaste.domain.usecase.auth

import ua.glebm.smartwaste.domain.datastore.AuthDataStore
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import ua.glebm.smartwaste.domain.usecase.core.ResultSuspendNoneParamsUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/28/2023
 */

class LogOutUseCase @Inject constructor(
    private val authDataStore: AuthDataStore,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler
) : ResultSuspendNoneParamsUseCase<Unit>(
    sessionStatusHandler = sessionStatusHandler,
    useCaseLogger = useCaseLogger
) {

    override suspend fun invoke() = runCatching {
        withContext(Dispatchers.IO) {
            authDataStore.removeAccessToken()
        }
    }
}
