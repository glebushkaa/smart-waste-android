package ua.glebm.smartwaste.domain.usecase.favorite

import ua.glebm.smartwaste.domain.repository.FavoritesRepository
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import ua.glebm.smartwaste.domain.usecase.favorite.GetFavoritesUseCase.Params
import ua.glebm.smartwaste.domain.usecase.core.ResultSuspendUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import ua.glebm.smartwaste.model.Guide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/30/2023
 */

class GetFavoritesUseCase @Inject constructor(
    private val favoriteRepository: FavoritesRepository,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler
) : ResultSuspendUseCase<List<Guide>, Params>(
    useCaseLogger = useCaseLogger,
    sessionStatusHandler = sessionStatusHandler
) {

    override suspend fun invoke(params: Params) = runCatching {
        withContext(Dispatchers.IO) {
            favoriteRepository.searchFavorites(query = params.query)
        }
    }

    data class Params(val query: String) : UseCase.Params
}
