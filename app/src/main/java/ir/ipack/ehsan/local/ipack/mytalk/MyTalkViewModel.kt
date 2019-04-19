package ir.ipack.ehsan.local.ipack.mytalk

import android.app.Application
import androidx.lifecycle.Transformations
import ir.ipack.ehsan.local.ipack.BaseViewModel
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyTalkViewModel(
    override val context: Application,
    private val repository: Repository
) : BaseViewModel(context) {

    fun getTalkCycleStream() = repository.getTalkCycleStream()

    fun getTalkUsageStreamLive() = Transformations.map(repository.getTalkUsageStreamLive()) {
        it.firstOrNull()
    }

    fun updateTalkCycle(cycle: CycleEntity) = repository.updateTalkCycle(cycle)

    override fun updateBaseCost(changeAmount: Int) {
        repository.updateBaseCost(changeAmount)
    }
}