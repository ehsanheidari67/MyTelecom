package ir.ipack.ehsan.local.ipack.core.domain.usage

import ir.ipack.ehsan.local.ipack.core.domain.MediatorUseCase
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository

class GetTextUsageUseCase(private val repository: Repository) : MediatorUseCase<Unit, UsageEntity>() {
    override fun execute(parameters: Unit) {
        val usage = repository.getTextUsageResult()

        result.addSource(usage) {
            result.value = it
        }
    }
}