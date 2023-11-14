package ua.glebm.smartwaste.data.usecase

import ua.glebm.smartwaste.domain.usecase.core.UseCaseLogger
import ua.glebm.smartwaste.log.error
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

class UseCaseLoggerImpl @Inject constructor() : UseCaseLogger {

    override fun logException(tag: String, throwable: Throwable) {
        error(tag, throwable)
    }
}
