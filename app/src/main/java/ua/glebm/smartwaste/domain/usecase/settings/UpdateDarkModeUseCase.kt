package ua.glebm.smartwaste.domain.usecase.settings

import ua.glebm.smartwaste.domain.datastore.SettingsDataStore
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import ua.glebm.smartwaste.domain.usecase.core.ResultSuspendUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import ua.glebm.smartwaste.domain.usecase.settings.UpdateDarkModeUseCase.Params
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

class UpdateDarkModeUseCase @Inject constructor(
    private val settingsDataStore: SettingsDataStore,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler
) : ResultSuspendUseCase<Unit, Params>(
    useCaseLogger = useCaseLogger,
    sessionStatusHandler = sessionStatusHandler
) {

    override suspend fun invoke(params: Params) = runCatching {
        withContext(Dispatchers.IO) {
            settingsDataStore.updateDarkMode(params.enabled)
        }
    }

    data class Params(
        val enabled: Boolean,
    ) : UseCase.Params
}
