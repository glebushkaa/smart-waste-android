package ua.glebm.smartwaste.domain.usecase.guides

import ua.glebm.smartwaste.domain.repository.GuideRepository
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import ua.glebm.smartwaste.domain.usecase.core.ResultSuspendUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import ua.glebm.smartwaste.domain.usecase.guides.GetGuidesByQueryUseCase.Params
import ua.glebm.smartwaste.model.Guide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

class GetGuidesByQueryUseCase @Inject constructor(
    private val guideRepository: GuideRepository,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler
) : ResultSuspendUseCase<List<Guide>, Params>(
    useCaseLogger = useCaseLogger,
    sessionStatusHandler = sessionStatusHandler
) {

    override suspend fun invoke(params: Params) = runCatching {
        withContext(Dispatchers.IO) {
            guideRepository.searchGuides(params.query)
        }
    }

    data class Params(
        val query: String,
    ) : UseCase.Params
}
