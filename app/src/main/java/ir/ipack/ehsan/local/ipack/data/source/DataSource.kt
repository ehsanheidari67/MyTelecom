package ir.ipack.ehsan.local.ipack.data.source

import androidx.lifecycle.LiveData
import ir.ipack.ehsan.local.ipack.core.exception.Failure
import ir.ipack.ehsan.local.ipack.core.functional.Either
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity

interface DataSource {
    fun getDataCycleStreamLive(): LiveData<Either<Failure, CycleEntity>>
    fun getTalkCycleStreamLive(): LiveData<Either<Failure, CycleEntity>>
    fun getTextCycleStreamLive(): LiveData<Either<Failure, CycleEntity>>

    fun updateDataCycle(cycle: CycleEntity)
    fun updateTalkCycle(cycle: CycleEntity)
    fun updateTextCycle(cycle: CycleEntity)

    fun updateBaseCost(changeAmount: Int)

    fun getUsagesStreamLive(): LiveData<Either<Failure, List<UsageEntity>>>
    fun getTextUsageStreamLive(): LiveData<Either<Failure, UsageEntity>>
    fun getTalkUsageStreamLive(): LiveData<Either<Failure, UsageEntity>>

    fun getBasePlanStreamLive(): LiveData<Either<Failure, BasePlanEntity>>
}