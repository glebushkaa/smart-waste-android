package ua.glebm.smartwaste.domain.usecase.core

import ua.glebm.smartwaste.session.api.SessionStatusHandler

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

abstract class ResultSuspendUseCase<Type : Any, in Params : UseCase.Params>(
    private val useCaseLogger: UseCaseLogger,
    private val sessionStatusHandler: SessionStatusHandler
) : UseCase<Type, Params> {

    abstract suspend operator fun invoke(params: Params): Result<Type>

    @Suppress("TooGenericExceptionCaught")
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
