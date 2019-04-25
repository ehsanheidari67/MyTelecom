package ir.ipack.ehsan.local.ipack.mydata

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ir.ipack.ehsan.local.ipack.BaseViewModel
import ir.ipack.ehsan.local.ipack.core.exception.Failure
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyDataViewModel(
    override val context: Application,
    private val repository: Repository
) : BaseViewModel(context) {

    private val _failure = MediatorLiveData<Failure>()
    val failure: LiveData<Failure>
        get() = _failure
    private val _usages = MediatorLiveData<List<UsageEntity>>()
    val usages: LiveData<List<UsageEntity>>
        get() = _usages
    private val _cycle = MediatorLiveData<CycleEntity>()
    val cycle: LiveData<CycleEntity>
        get() = _cycle

    private val dataUsages = repository.getUsagesStreamLive()
    private val dataCycle = repository.getDataCycleStreamLive()

    init {
        _usages.addSource(dataUsages) {
            it.either(::handleFailure, ::handleUsages)
        }

        _cycle.addSource(dataCycle) {
            it.either(::handleFailure, ::handleCycle)
        }
    }

    private fun handleCycle(cycleEntity: CycleEntity) {
        _cycle.value = cycleEntity
    }

    private fun handleUsages(list: List<UsageEntity>) {
        _usages.value = list
    }

    private fun handleFailure(failure: Failure) {
        _failure.value = Failure
    }

    fun updateDataCycle(cycle: CycleEntity) = repository.updateDataCycle(cycle)

    override fun updateBaseCost(changeAmount: Int) = repository.updateBaseCost(changeAmount)
}