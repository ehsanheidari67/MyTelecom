package ir.ipack.ehsan.local.ipack.core.domain.baseplan

import ir.ipack.ehsan.local.ipack.core.domain.MediatorUseCase
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository

class GetBasePlan(private val repository: Repository) : MediatorUseCase<Unit, BasePlanEntity>() {
    override fun execute(parameters: Unit) {
        val basePlan = repository.getBasePlanResult()
        result.addSource(basePlan) {
            result.value = it
        }
    }
}