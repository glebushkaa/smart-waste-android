package ua.glebm.smartwaste.ui.screen.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import ua.glebm.smartwaste.core.android.BaseViewModel
import ua.glebm.smartwaste.core.android.stateReducerFlow
import ua.glebm.smartwaste.model.Quest
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

@HiltViewModel
class ProfileViewModel @Inject constructor() : BaseViewModel() {

    val dailyList = listOf(
        Quest(
            id = 32,
            title = "Complete 5 buckets",
            totalValue = 5,
            completeValue = 3,
        ),
        Quest(
            id = 43,
            title = "Complete 10 buckets",
            totalValue = 10,
            completeValue = 10,
        ),
    )
    val generalList = listOf(
        Quest(
            id = 1,
            title = "Complete 5 buckets",
            totalValue = 5,
            completeValue = 5,
        ),
        Quest(
            id = 2,
            title = "Complete 10 buckets",
            totalValue = 10,
            completeValue = 0,
        ),
        Quest(
            id = 3,
            title = "Complete 10 buckets",
            totalValue = 10,
            completeValue = 10,
        ),
        Quest(
            id = 5,
            title = "Complete 10 buckets",
            totalValue = 10,
            completeValue = 0,
        ),
    )

    val _state = ProfileState(
        username = "gle.bushkaa",
        email = "gleb.mokryy@gmail.com",
        doneBuckets = 10,
        level = 17,
        days = 5,
        requiredLevelProgress = 500,
        completedLevelProgress = 345,
        dailyQuests = dailyList,
        generalQuests = generalList,
    )

    val state = stateReducerFlow(
        initialState = _state,
        reduceState = ::handleEvent,
    )

    private fun handleEvent(currentState: ProfileState, event: ProfileEvent): ProfileState {
        return currentState
    }
}
