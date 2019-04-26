package ir.ipack.ehsan.local.ipack.data.source.local

import androidx.lifecycle.LiveData
import ir.ipack.ehsan.local.ipack.core.exception.Failure
import ir.ipack.ehsan.local.ipack.core.extension.map
import ir.ipack.ehsan.local.ipack.core.functional.Either
import ir.ipack.ehsan.local.ipack.core.functional.Either.Left
import ir.ipack.ehsan.local.ipack.core.functional.Either.Right
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.DataSource
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum

class LocalDataSource(private val dataPersistence: DataPersistence) :
    DataSource {

    override fun getTalkUsageStreamLive(): LiveData<Either<Failure, UsageEntity>> = talkUsageLive

    override fun getTextUsageStreamLive(): LiveData<Either<Failure, UsageEntity>> = textUsageLive

    override fun getUsagesStreamLive(): LiveData<Either<Failure, List<UsageEntity>>> = appUsageLive

    override fun getDataCycleStreamLive(): LiveData<Either<Failure, CycleEntity>> =
        dataPersistence.getCycleByTypeLive(CycleTypeEnum.INTERNET).map {
            it.firstOrNull()?.let { cycleEntity ->
                Right(cycleEntity)
            } ?: Left(Failure)
        }

    override fun getTalkCycleStreamLive(): LiveData<Either<Failure, CycleEntity>> =
        dataPersistence.getCycleByTypeLive(CycleTypeEnum.TALK).map {
            it.firstOrNull()?.let { cycleEntity ->
                Right(cycleEntity)
            } ?: Left(Failure)
        }


    override fun getTextCycleStreamLive(): LiveData<Either<Failure, CycleEntity>> =
        dataPersistence.getCycleByTypeLive(CycleTypeEnum.TEXT).map {
            it.firstOrNull()?.let { cycleEntity ->
                Right(cycleEntity)
            } ?: Left(Failure)
        }

    override fun getBasePlanStreamLive(): LiveData<Either<Failure, BasePlanEntity>> =
        dataPersistence.getBasePlan().map {
            it.firstOrNull()?.let { basePlanEntity ->
                Right(basePlanEntity)
            } ?: Left(Failure)
        }

    override fun updateDataCycle(cycle: CycleEntity) {
        dataPersistence.setCycle(cycle)
    }

    override fun updateTalkCycle(cycle: CycleEntity) {
        dataPersistence.setCycle(cycle)
    }

    override fun updateTextCycle(cycle: CycleEntity) {
        dataPersistence.setCycle(cycle)
    }

    override fun updateBaseCost(changeAmount: Int) {
        dataPersistence.updateBasePlan(changeAmount)
    }

    private val talkUsageLive: LiveData<Either<Failure, UsageEntity>>
        get() = dataPersistence.getUsageByTypeLive(CycleTypeEnum.TALK).map {
            it.firstOrNull()?.let { usageEntity ->
                Right(usageEntity)
            } ?: Left(Failure)
        }
    private val textUsageLive: LiveData<Either<Failure, UsageEntity>>
        get() = dataPersistence.getUsageByTypeLive(CycleTypeEnum.TEXT).map {
            it.firstOrNull()?.let { usageEntity ->
                Right(usageEntity)
            } ?: Left(Failure)
        }
    private val appUsageLive: LiveData<Either<Failure, List<UsageEntity>>>
        get() = dataPersistence.getUsageByTypeLive(CycleTypeEnum.INTERNET).map {
            Right(it)
        }
}

interface DataPersistence {
    fun getUsageByTypeLive(cycleTypeEnum: CycleTypeEnum): LiveData<List<UsageEntity>>
    fun getCycleByTypeLive(cycleTypeEnum: CycleTypeEnum): LiveData<List<CycleEntity>>
    fun setCycle(cycle: CycleEntity)
    fun getBasePlan(): LiveData<List<BasePlanEntity>>
    fun setBasePlan(basePlanEntity: BasePlanEntity)
    fun updateBasePlan(changeAmount: Int)
}