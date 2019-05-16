package ir.ipack.ehsan.local.ipack.core.domain.basecost

import ir.ipack.ehsan.local.ipack.core.domain.MediatorUseCase
import ir.ipack.ehsan.local.ipack.data.source.Repository

class UpdateBaseCostUseCase(private val repository: Repository) : MediatorUseCase<Int, Unit>() {
    override fun execute(parameters: Int) {
        repository.updateBaseCost(parameters)
    }
}