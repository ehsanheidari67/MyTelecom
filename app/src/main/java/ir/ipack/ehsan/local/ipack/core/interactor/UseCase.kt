package ir.ipack.ehsan.local.ipack.core.interactor

import ir.ipack.ehsan.local.ipack.core.exception.Failure
import ir.ipack.ehsan.local.ipack.core.functional.Either

abstract class UseCase<Type : Any, Params> {
    abstract fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val job = run(params)
        onResult(job)
    }

    class None
}