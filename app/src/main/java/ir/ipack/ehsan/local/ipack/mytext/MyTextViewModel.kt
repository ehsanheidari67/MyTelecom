package ir.ipack.ehsan.local.ipack.mytext

import android.app.Application
import ir.ipack.ehsan.local.ipack.BaseViewModel
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyTextViewModel(
    override val context: Application,
    private val repository: Repository
) : BaseViewModel(context) {

    fun getTextCycleStream() = repository.getTextCycleStream()

    fun getTextUsageStream() = repository.getTextUsageStream()

    fun updateTextCycle(cycle: CycleEntity) = repository.updateTextCycle(cycle)

    override fun updateBaseCost(changeAmount: Int) {
        repository.updateBaseCost(changeAmount)
    }
}