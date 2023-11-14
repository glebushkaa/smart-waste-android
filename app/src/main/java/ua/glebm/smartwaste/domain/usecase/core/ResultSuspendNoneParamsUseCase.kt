package ua.glebm.smartwaste.domain.usecase.core

import ua.glebm.smartwaste.session.api.SessionStatusHandler

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

abstract class ResultSuspendNoneParamsUseCase<Type : Any>(
    private val useCaseLogger: UseCaseLogger,
    private val sessionStatusHandler: SessionStatusHandler
) : NoneParamsUseCase<Type> {

    abstract suspend operator fun invoke(): Result<Type>

    suspend fun <T, P> T.runCatching(block: suspend T.() -> P): Result<P> {
        return try {
            Result.success(block())
        } catch (throwable: Throwable) {
            useCaseLogger.logException(javaClass.simpleName, throwable)
            sessionStatusHandler.validateException(throwable)
            Result.failure(throwable)
        }
    }
}
