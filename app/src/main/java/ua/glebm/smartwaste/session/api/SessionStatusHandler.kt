package ua.glebm.smartwaste.session.api

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/31/2023.
 */

interface SessionStatusHandler {

    val sessionStatus: StateFlow<SessionStatus>

    /**
     * Function checks throwable whether it is http unauthorized exception or not
     * if it is then there will be called endSession()
     */
    fun validateException(throwable: Throwable)

    fun endSession()
}