package ir.ipack.ehsan.local.ipack.core.domain

import androidx.lifecycle.MediatorLiveData
import ir.ipack.ehsan.local.ipack.core.result.Result

abstract class MediatorUseCase<in P, R> {
    protected val result = MediatorLiveData<Result<R>>()

    fun observe() = result

    abstract fun execute(parameters: P)
}