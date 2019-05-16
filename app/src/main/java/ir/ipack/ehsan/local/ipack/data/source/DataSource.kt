package ir.ipack.ehsan.local.ipack.data.source

import androidx.lifecycle.LiveData
import ir.ipack.ehsan.local.ipack.core.exception.Failure
import ir.ipack.ehsan.local.ipack.core.result.Either
import ir.ipack.ehsan.local.ipack.core.result.Result
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum

interface DataSource {
    fun getDataCycleStreamLive(): LiveData<Either<Failure, CycleEntity>>
    fun getTalkCycleStreamLive(): LiveData<Either<Failure, CycleEntity>>
    fun getTextCycleStreamLive(): LiveData<Either<Failure, CycleEntity>>

    fun updateCycle(cycle: CycleEntity)

    fun updateDataCycle(cycle: CycleEntity)
    fun updateTalkCycle(cycle: CycleEntity)
    fun updateTextCycle(cycle: CycleEntity)

    fun updateBaseCost(changeAmount: Int)

    fun getUsagesStreamLive(): LiveData<Either<Failure, List<UsageEntity>>>
    fun getTextUsageStreamLive(): LiveData<Either<Failure, UsageEntity>>
    fun getTalkUsageStreamLive(): LiveData<Either<Failure, UsageEntity>>

    fun getBasePlanStreamLive(): LiveData<Either<Failure, BasePlanEntity>>

    fun getAppUsagesResult(): LiveData<Result<List<UsageEntity>>>
    fun getTextUsageResult(): LiveData<Result<UsageEntity>>
    fun getTalkUsageResult(): LiveData<Result<UsageEntity>>

    fun getCycleByTypeResult(cycleType: CycleTypeEnum): LiveData<Result<CycleEntity>>
    fun getBasePlanResult(): LiveData<Result<BasePlanEntity>>
}