package ir.ipack.ehsan.local.ipack.core.domain.usage

import ir.ipack.ehsan.local.ipack.core.domain.MediatorUseCase
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository

class GetDataUsageUseCase(private val repository: Repository) : MediatorUseCase<Unit, List<UsageEntity>>() {
    override fun execute(parameters: Unit) {
        val appUsages = repository.getAppUsagesResult()

        result.addSource(appUsages) {
            result.value = it
        }
    }
}