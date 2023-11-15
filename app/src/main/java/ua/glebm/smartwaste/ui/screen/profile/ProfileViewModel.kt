package ua.glebm.smartwaste.ui.screen.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import ua.glebm.smartwaste.core.android.BaseViewModel
import ua.glebm.smartwaste.core.android.stateReducerFlow
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@HiltViewModel
class ProfileViewModel @Inject constructor() : BaseViewModel() {

    val state = stateReducerFlow(
        initialState = ProfileState(),
        reduceState = ::handleEvent,
    )

    private fun handleEvent(currentState: ProfileState, event: ProfileEvent): ProfileState {
        return currentState
    }
}
