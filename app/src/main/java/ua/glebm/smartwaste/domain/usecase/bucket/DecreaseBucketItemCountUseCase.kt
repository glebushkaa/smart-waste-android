package ua.glebm.smartwaste.domain.usecase.bucket

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.glebm.smartwaste.domain.repository.BucketRepository
import ua.glebm.smartwaste.domain.usecase.bucket.DecreaseBucketItemCountUseCase.Params
import ua.glebm.smartwaste.domain.usecase.core.ResultSuspendUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

class DecreaseBucketItemCountUseCase @Inject constructor(
    private val bucketRepository: BucketRepository,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler,
) : ResultSuspendUseCase<Unit, Params>(
    useCaseLogger = useCaseLogger,
    sessionStatusHandler = sessionStatusHandler,
) {
    override suspend fun invoke(params: Params) = runCatching {
        withContext(Dispatchers.IO) {
            bucketRepository.decreaseItemCount(params.name)
        }
    }

    data class Params(
        val name: String,
    ) : UseCase.Params
}
