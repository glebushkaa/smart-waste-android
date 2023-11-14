package ua.glebm.smartwaste.app

import ua.glebm.smartwaste.core.android.BaseViewModel
import ua.glebm.smartwaste.session.api.SessionStatus
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import ua.glebm.smartwaste.domain.usecase.settings.CollectDarkModeUseCase
import ua.glebm.smartwaste.domain.usecase.settings.UpdateDarkModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val collectDarkModeUseCase: CollectDarkModeUseCase,
    private val updateDarkModeUseCase: UpdateDarkModeUseCase,
    private val sessionStatusHandler: SessionStatusHandler,
) : BaseViewModel() {

    val darkModeFlow: Flow<Boolean?>
        get() = collectDarkModeUseCase().getOrNull() ?: emptyFlow()

    val sessionStatusFlow: StateFlow<SessionStatus>
        get() = sessionStatusHandler.sessionStatus

    fun saveSystemDarkMode(
        systemDarkModeEnabled: Boolean,
    ) = viewModelScope.launch(Dispatchers.IO) {
        val count = darkModeFlow.count()
        if (count != 0) return@launch
        val params = UpdateDarkModeUseCase.Params(systemDarkModeEnabled)
        updateDarkModeUseCase(params)
    }
}
