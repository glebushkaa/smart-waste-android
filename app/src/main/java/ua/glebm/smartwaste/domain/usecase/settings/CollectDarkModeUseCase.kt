package ua.glebm.smartwaste.domain.usecase.settings

import ua.glebm.smartwaste.domain.datastore.SettingsDataStore
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import ua.glebm.smartwaste.domain.usecase.core.ResultNoneParamsUseCase
import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

class CollectDarkModeUseCase @Inject constructor(
    private val settingsDataStore: SettingsDataStore,
    useCaseLogger: UseCaseLogger,
    sessionStatusHandler: SessionStatusHandler
) : ResultNoneParamsUseCase<Flow<Boolean?>>(
    useCaseLogger = useCaseLogger,
    sessionStatusHandler = sessionStatusHandler
) {

    override fun invoke() = runCatching { settingsDataStore.darkModeFlow }
}
