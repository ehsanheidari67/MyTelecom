package ir.ipack.ehsan.local.ipack.myplan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ir.ipack.ehsan.local.ipack.core.exception.Failure
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyPlanViewModel(
    context: Application,
    repository: Repository
) : AndroidViewModel(context) {

    private val _failure = MediatorLiveData<Failure>()
    val failure: LiveData<Failure>
        get() = _failure
    private val _basePlan = MediatorLiveData<BasePlanEntity>()
    val basePlan: LiveData<BasePlanEntity>
        get() = _basePlan

    private val _dataCycle = MediatorLiveData<CycleEntity>()
    val dataCycle: LiveData<CycleEntity>
        get() = _dataCycle

    private val _talkCycle = MediatorLiveData<CycleEntity>()
    val talkCycle: LiveData<CycleEntity>
        get() = _talkCycle

    private val _textCycle = MediatorLiveData<CycleEntity>()
    val textCycle: LiveData<CycleEntity>
        get() = _textCycle

    private val basePlanResult = repository.getBasePlanStreamLive()
    private val textCycleResult = repository.getTextCycleStreamLive()
    private val talkCycleResult = repository.getTalkCycleStreamLive()
    private val dataCycleResult = repository.getDataCycleStreamLive()

    init {
        _basePlan.addSource(basePlanResult) {
            it.either(::handleFailure, ::handleBasePlan)
        }

        _textCycle.addSource(textCycleResult) {
            it.either(::handleFailure, ::handleTextCycle)
        }
        _talkCycle.addSource(talkCycleResult) {
            it.either(::handleFailure, ::handleTalkCycle)
        }
        _dataCycle.addSource(dataCycleResult) {
            it.either(::handleFailure, ::handleDataCycle)
        }
    }

    private fun handleTextCycle(cycleEntity: CycleEntity) {
        _textCycle.value = cycleEntity
    }

    private fun handleTalkCycle(cycleEntity: CycleEntity) {
        _talkCycle.value = cycleEntity
    }

    private fun handleDataCycle(cycleEntity: CycleEntity) {
        _dataCycle.value = cycleEntity
    }

    private fun handleBasePlan(basePlanEntity: BasePlanEntity) {
        _basePlan.value = basePlanEntity
    }

    private fun handleFailure(failure: Failure) {

    }
}
