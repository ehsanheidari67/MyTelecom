package ir.ipack.ehsan.local.ipack.mytalk

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ir.ipack.ehsan.local.ipack.BaseViewModel
import ir.ipack.ehsan.local.ipack.core.exception.Failure
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyTalkViewModel(
    override val context: Application,
    private val repository: Repository
) : BaseViewModel(context) {

    private val _failure = MediatorLiveData<Failure>()
    val failure: LiveData<Failure>
        get() = _failure
    private val _usage = MediatorLiveData<UsageEntity>()
    val usage: LiveData<UsageEntity>
        get() = _usage
    private val _cycle = MediatorLiveData<CycleEntity>()
    val cycle: LiveData<CycleEntity>
        get() = _cycle

    private val talkUsage = repository.getTalkUsageStreamLive()
    private val talkCycle = repository.getTalkCycleStreamLive()

    init {
        _usage.addSource(talkUsage) {
            it.either(::handleFailure, ::handleUsage)
        }

        _cycle.addSource(talkCycle) {
            it.either(::handleFailure, ::handleCycle)
        }
    }

    private fun handleCycle(cycleEntity: CycleEntity) {
        _cycle.value = cycleEntity
    }

    private fun handleUsage(usageEntity: UsageEntity) {
        _usage.value = usageEntity
    }

    private fun handleFailure(failure: Failure) {
        _failure.value = Failure
    }

    fun updateTalkCycle(cycle: CycleEntity) = repository.updateTalkCycle(cycle)

    override fun updateBaseCost(changeAmount: Int) {
        repository.updateBaseCost(changeAmount)
    }
}