package ir.ipack.ehsan.local.ipack.mydata

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ir.ipack.ehsan.local.ipack.BaseViewModel
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyDataViewModel(
    override val context: Application,
    private val repository: Repository
) : BaseViewModel(context) {

    private val usagesLive = repository.getUsagesStreamLive(context)
    private var presentationUsagesLive: LiveData<List<UsageEntity>>? = null

    fun getDataCycleStream() = repository.getDataCycleStream()

    fun getUsagesStream() = repository.getUsagesStream(context)

    fun getUsagesStreamLive(): LiveData<List<UsageEntity>> {
        return Transformations.map(usagesLive) {
            it.map {
                it.usageImage = 12
                it
            }
        }.also {
            presentationUsagesLive = it
        }
    }

    fun updateDataCycle(cycle: CycleEntity) = repository.updateDataCycle(cycle)

    override fun updateBaseCost(changeAmount: Int) {
        repository.updateBaseCost(changeAmount)
    }
}