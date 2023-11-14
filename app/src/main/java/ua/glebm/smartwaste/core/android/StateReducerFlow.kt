package ua.glebm.smartwaste.core.android

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

interface StateReducerFlow<State, Action> : StateFlow<State> {

    fun handleEvent(action: Action)
}
