package ir.ipack.ehsan.local.ipack.mytext

import android.app.Application
import ir.ipack.ehsan.local.ipack.BaseViewModel
import ir.ipack.ehsan.local.ipack.data.Cycle
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyTextViewModel(
    override val context: Application,
    private val repository: Repository
) : BaseViewModel(context) {

    fun getTextCycleStream() = repository.getTextCycleStream()

    fun getTextUsageStream() = repository.getTextUsageStream()

    fun updateTextCycle(cycle: Cycle) = repository.updateTextCycle(cycle)

    override fun updateBaseCost(changeAmount: Int) {
        repository.updateBaseCost(changeAmount)
    }
}