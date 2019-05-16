package ir.ipack.ehsan.local.ipack.core.domain.cycle

import ir.ipack.ehsan.local.ipack.core.domain.MediatorUseCase
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum

class GetCycleByTypeUseCase(private val repository: Repository) : MediatorUseCase<CycleTypeEnum, CycleEntity>() {
    override fun execute(parameters: CycleTypeEnum) {
        val cycleEntityResult = repository.getCycleByTypeResult(parameters)
        result.addSource(cycleEntityResult) {
            result.value = it
        }
    }
}