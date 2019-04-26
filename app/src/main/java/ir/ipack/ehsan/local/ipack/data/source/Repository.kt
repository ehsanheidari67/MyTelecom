package ir.ipack.ehsan.local.ipack.data.source

import androidx.lifecycle.LiveData
import ir.ipack.ehsan.local.ipack.core.exception.Failure
import ir.ipack.ehsan.local.ipack.core.functional.Either
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.local.LocalDataSource

class Repository(private val localDataSource: LocalDataSource) : DataSource {

    override fun getBasePlanStreamLive(): LiveData<Either<Failure, BasePlanEntity>> =
        localDataSource.getBasePlanStreamLive()

    override fun getDataCycleStreamLive(): LiveData<Either<Failure, CycleEntity>> =
        localDataSource.getDataCycleStreamLive()

    override fun getTalkCycleStreamLive(): LiveData<Either<Failure, CycleEntity>> =
        localDataSource.getTalkCycleStreamLive()

    override fun getTextCycleStreamLive(): LiveData<Either<Failure, CycleEntity>> =
        localDataSource.getTextCycleStreamLive()

    override fun updateDataCycle(cycle: CycleEntity) = localDataSource.updateDataCycle(cycle)

    override fun updateTalkCycle(cycle: CycleEntity) = localDataSource.updateTalkCycle(cycle)

    override fun updateTextCycle(cycle: CycleEntity) = localDataSource.updateTextCycle(cycle)

    override fun getUsagesStreamLive(): LiveData<Either<Failure, List<UsageEntity>>> =
        localDataSource.getUsagesStreamLive()

    override fun getTextUsageStreamLive(): LiveData<Either<Failure, UsageEntity>> =
        localDataSource.getTextUsageStreamLive()

    override fun getTalkUsageStreamLive(): LiveData<Either<Failure, UsageEntity>> =
        localDataSource.getTalkUsageStreamLive()

    override fun updateBaseCost(changeAmount: Int) = localDataSource.updateBaseCost(changeAmount)
}