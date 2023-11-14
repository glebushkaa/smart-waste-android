package ua.glebm.smartwaste.session.impl

import ua.glebm.smartwaste.session.api.SessionStatus
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ua.glebm.smartwaste.log.error
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/31/2023.
 */

class SessionStatusHandlerImpl @Inject constructor() : SessionStatusHandler {

    private val _sessionStatus = MutableStateFlow(SessionStatus(true))
    override val sessionStatus = _sessionStatus.asStateFlow()

    override fun validateException(throwable: Throwable) {
        val httpException = throwable as? retrofit2.HttpException ?: run {
            error("SessionStatusHandlerImpl", throwable) { "not http exception" }
            return
        }
        val code = httpException.code()
        error("SessionStatusHandlerImpl", httpException) { "code: $code" }
        if (code == HttpsURLConnection.HTTP_UNAUTHORIZED) {
            endSession()
        }
    }

    override fun endSession() {
        val status = SessionStatus(false)
        _sessionStatus.tryEmit(status)
    }
}