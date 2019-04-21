package ir.ipack.ehsan.local.ipack.mytext

import android.app.Application
import ir.ipack.ehsan.local.ipack.BaseViewModel
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyTextViewModel(
    override val context: Application,
    private val repository: Repository
) : BaseViewModel(context) {

    fun getTextCycleStreamLive() = repository.getTextCycleStreamLive()

    fun getTextUsageStreamLive() = repository.getTextUsageStreamLive()

    fun updateTextCycle(cycle: CycleEntity) = repository.updateTextCycle(cycle)

    override fun updateBaseCost(changeAmount: Int) = repository.updateBaseCost(changeAmount)
}