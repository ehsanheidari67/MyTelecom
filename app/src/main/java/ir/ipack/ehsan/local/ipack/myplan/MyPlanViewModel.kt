package ir.ipack.ehsan.local.ipack.myplan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ir.ipack.ehsan.local.ipack.core.domain.baseplan.GetBasePlan
import ir.ipack.ehsan.local.ipack.core.domain.cycle.GetCycleByTypeUseCase
import ir.ipack.ehsan.local.ipack.core.exception.Failure
import ir.ipack.ehsan.local.ipack.core.result.Result
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum

class MyPlanViewModel(
    context: Application,
    getBasePlan: GetBasePlan,
    getDataCycleByTypeUseCase: GetCycleByTypeUseCase,
    getTalkCycleByTypeUseCase: GetCycleByTypeUseCase,
    getTextCycleByTypeUseCase: GetCycleByTypeUseCase
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

    init {
        getBasePlan.execute(Unit)
        _basePlan.addSource(getBasePlan.observe()) {
            (it as? Result.Success)?.let { result ->
                _basePlan.value = result.data
            }
        }

        getTextCycleByTypeUseCase.execute(CycleTypeEnum.TEXT)
        _textCycle.addSource(getTextCycleByTypeUseCase.observe()) {
            (it as? Result.Success)?.let { result ->
                _textCycle.value = result.data
            }
        }
        getTalkCycleByTypeUseCase.execute(CycleTypeEnum.TALK)
        _talkCycle.addSource(getTalkCycleByTypeUseCase.observe()) {
            (it as? Result.Success)?.let { result ->
                _talkCycle.value = result.data
            }
        }
        getDataCycleByTypeUseCase.execute(CycleTypeEnum.INTERNET)
        _dataCycle.addSource(getDataCycleByTypeUseCase.observe()) {
            (it as? Result.Success)?.let { result ->
                _dataCycle.value = result.data
            }
        }
    }
}
