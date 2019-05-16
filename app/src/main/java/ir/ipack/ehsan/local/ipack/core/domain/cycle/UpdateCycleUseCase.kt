package ir.ipack.ehsan.local.ipack.core.domain.cycle

import ir.ipack.ehsan.local.ipack.core.domain.MediatorUseCase
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository

class UpdateCycleUseCase(private val repository: Repository) : MediatorUseCase<CycleEntity, Unit>() {
    override fun execute(parameters: CycleEntity) {
        repository.updateDataCycle(parameters)
    }
}