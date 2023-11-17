package ua.glebm.smartwaste.domain.usecase.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.glebm.smartwaste.domain.repository.AuthRepository
import ua.glebm.smartwaste.domain.usecase.core.ResultSuspendNoneParamsUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import ua.glebm.smartwaste.model.Quest
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/17/2023
 */

class GetQuestsUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler,
) : ResultSuspendNoneParamsUseCase<List<Quest>>(
    useCaseLogger,
    sessionStatusHandler,
) {

    override suspend fun invoke() = runCatching {
        withContext(Dispatchers.IO) {
            authRepository.getQuests()
        }
    }
}
