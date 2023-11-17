package ua.glebm.smartwaste.ui.screen.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ua.glebm.smartwaste.core.android.BaseViewModel
import ua.glebm.smartwaste.core.android.stateReducerFlow
import ua.glebm.smartwaste.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseViewModel() {

    val state = stateReducerFlow(
        initialState = ProfileState(),
        reduceState = ::handleEvent,
    )

    init {
        initProfile()
    }

    private fun initProfile() = viewModelScope.launch(Dispatchers.IO) {
        val user = async { authRepository.getUser() }
        val quests = async { authRepository.getQuests() }
        val event = ProfileEvent.UserInfoLoaded(
            user = user.await(),
            quests = quests.await(),
        )
        state.handleEvent(event)
    }

    private fun handleEvent(currentState: ProfileState, event: ProfileEvent): ProfileState {
        when (event) {
            is ProfileEvent.UserInfoLoaded -> {
                val level = event.user.score / 500
                val completedLevelProgress = event.user.score % 500
                return currentState.copy(
                    username = event.user.username,
                    email = event.user.email,
                    quests = event.quests,
                    requiredLevelProgress = 500,
                    completedLevelProgress = completedLevelProgress,
                    level = level,
                    doneBuckets = event.user.buckets,
                    days = event.user.days,
                )
            }
        }
    }
}
