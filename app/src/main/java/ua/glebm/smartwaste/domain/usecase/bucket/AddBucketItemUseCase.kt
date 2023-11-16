package ua.glebm.smartwaste.domain.usecase.bucket

import ua.glebm.smartwaste.domain.repository.BucketRepository
import ua.glebm.smartwaste.domain.usecase.bucket.AddBucketItemUseCase.Params
import ua.glebm.smartwaste.domain.usecase.core.ResultSuspendUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import ua.glebm.smartwaste.model.BucketItem
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

class AddBucketItemUseCase @Inject constructor(
    private val bucketRepository: BucketRepository,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler,
) : ResultSuspendUseCase<Unit, Params>(
    useCaseLogger = useCaseLogger,
    sessionStatusHandler = sessionStatusHandler,
) {

    override suspend fun invoke(params: Params) = runCatching {
        bucketRepository.addItem(params.bucketItem)
    }

    data class Params(
        val bucketItem: BucketItem,
    ) : UseCase.Params
}
