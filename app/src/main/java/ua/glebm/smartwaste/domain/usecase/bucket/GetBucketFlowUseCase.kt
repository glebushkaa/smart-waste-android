package ua.glebm.smartwaste.domain.usecase.bucket

import kotlinx.coroutines.flow.Flow
import ua.glebm.smartwaste.domain.repository.BucketRepository
import ua.glebm.smartwaste.domain.usecase.core.ResultNoneParamsUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import ua.glebm.smartwaste.model.BucketItem
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

class GetBucketFlowUseCase @Inject constructor(
    private val bucketRepository: BucketRepository,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler,
) : ResultNoneParamsUseCase<Flow<List<BucketItem>>>(
    useCaseLogger = useCaseLogger,
    sessionStatusHandler = sessionStatusHandler,
) {
    override fun invoke() = runCatching {
        bucketRepository.getBucketFlow()
    }
}
