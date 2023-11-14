package ua.glebm.smartwaste.domain.usecase.recycle

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.glebm.smartwaste.domain.repository.RecycleRepository
import ua.glebm.smartwaste.domain.usecase.core.ResultSuspendNoneParamsUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import ua.glebm.smartwaste.model.RecyclePoint
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

class GetRecyclePointUseCase @Inject constructor(
    private val recycleRepository: RecycleRepository,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler,
) : ResultSuspendNoneParamsUseCase<List<RecyclePoint>>(
    useCaseLogger = useCaseLogger,
    sessionStatusHandler = sessionStatusHandler,
) {

    override suspend fun invoke() = runCatching {
        withContext(Dispatchers.IO) {
            recycleRepository.getRecyclePoints()
        }
    }
}
