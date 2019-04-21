package ir.ipack.ehsan.local.ipack.mydata

import android.app.Application
import androidx.lifecycle.LiveData
import ir.ipack.ehsan.local.ipack.BaseViewModel
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyDataViewModel(
    override val context: Application,
    private val repository: Repository
) : BaseViewModel(context) {
    fun getDataCycleStreamLive() = repository.getDataCycleStreamLive()

    fun getUsagesStreamLive(): LiveData<List<UsageEntity>> = repository.getUsagesStreamLive()

    fun updateDataCycle(cycle: CycleEntity) = repository.updateDataCycle(cycle)

    override fun updateBaseCost(changeAmount: Int) = repository.updateBaseCost(changeAmount)
}