package ua.glebm.smartwaste.domain.usecase.bucket

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.glebm.smartwaste.domain.repository.BucketRepository
import ua.glebm.smartwaste.domain.usecase.core.ResultSuspendNoneParamsUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/17/2023
 */

class ClearBucketUseCase @Inject constructor(
    private val bucketRepository: BucketRepository,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler,
) : ResultSuspendNoneParamsUseCase<Unit>(
    useCaseLogger = useCaseLogger,
    sessionStatusHandler = sessionStatusHandler,
) {

    override suspend fun invoke() = runCatching {
        withContext(Dispatchers.IO) {
            bucketRepository.dumpBucket()
            bucketRepository.clearBucket()
        }
    }
}
