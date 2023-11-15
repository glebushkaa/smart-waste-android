package ua.glebm.smartwaste.ui.screen.camera

import dagger.hilt.android.lifecycle.HiltViewModel
import ua.glebm.smartwaste.core.android.BaseViewModel
import ua.glebm.smartwaste.core.android.stateReducerFlow
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@HiltViewModel
class CameraViewModel @Inject constructor() : BaseViewModel() {

    val state = stateReducerFlow(
        initialState = CameraState(),
        reduceState = ::handleEvent,
    )

    private fun handleEvent(currentState: CameraState, event: CameraEvent): CameraState {
        return when (event) {
            is CameraEvent.SendImageUri -> {
                currentState.copy(imageUri = event.imageUri)
            }
        }
    }
}
