package ua.glebm.smartwaste.domain.usecase.core

import ua.glebm.smartwaste.session.api.SessionStatusHandler

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

abstract class ResultUseCase<Type : Any, in Params : UseCase.Params>(
    private val useCaseLogger: UseCaseLogger,
    private val sessionStatusHandler: SessionStatusHandler
) : UseCase<Type, Params> {

    abstract operator fun invoke(params: Params): Result<Type>

    @Suppress("TooGenericExceptionCaught")
    fun <T, P> T.runCatching(block: T.() -> P): Result<P> {
        return try {
            Result.success(block())
        } catch (throwable: Throwable) {
            useCaseLogger.logException(javaClass.simpleName, throwable)
            sessionStatusHandler.validateException(throwable)
            Result.failure(throwable)
        }
    }
}
