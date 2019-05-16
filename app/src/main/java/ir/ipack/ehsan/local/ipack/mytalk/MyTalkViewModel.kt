package ir.ipack.ehsan.local.ipack.mytalk

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ir.ipack.ehsan.local.ipack.BaseViewModel
import ir.ipack.ehsan.local.ipack.core.domain.basecost.UpdateBaseCostUseCase
import ir.ipack.ehsan.local.ipack.core.domain.cycle.GetCycleByTypeUseCase
import ir.ipack.ehsan.local.ipack.core.domain.cycle.UpdateCycleUseCase
import ir.ipack.ehsan.local.ipack.core.domain.usage.GetTalkUsageUseCase
import ir.ipack.ehsan.local.ipack.core.exception.Failure
import ir.ipack.ehsan.local.ipack.core.result.Result
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum

class MyTalkViewModel(
    override val context: Application,
    getTalkUsageUseCase: GetTalkUsageUseCase,
    getCycleByTypeUseCase: GetCycleByTypeUseCase,
    private val updateCycleUseCase: UpdateCycleUseCase,
    private val baseCostUseCase: UpdateBaseCostUseCase
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

    init {
        getTalkUsageUseCase.execute(Unit)
        _usage.addSource(getTalkUsageUseCase.observe()) {
            (it as? Result.Success)?.let { result ->
                _usage.value = result.data
            }
        }

        getCycleByTypeUseCase.execute(CycleTypeEnum.TALK)
        _cycle.addSource(getCycleByTypeUseCase.observe()) {
            (it as? Result.Success)?.let { result ->
                _cycle.value = result.data
            }
        }
    }

    fun updateTalkCycle(cycle: CycleEntity) = updateCycleUseCase.execute(cycle)

    override fun updateBaseCost(changeAmount: Int) {
        baseCostUseCase.execute(changeAmount)
    }
}