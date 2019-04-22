package ir.ipack.ehsan.local.ipack.data.source

import androidx.lifecycle.LiveData
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.local.LocalDataSource

class Repository(private val localDataSource: LocalDataSource) : DataSource {

    override fun getBasePlanStreamLive(): LiveData<BasePlanEntity?> = localDataSource.getBasePlanStreamLive()

    override fun getDataCycleStreamLive(): LiveData<CycleEntity?> = localDataSource.getDataCycleStreamLive()

    override fun getTalkCycleStreamLive(): LiveData<CycleEntity?> = localDataSource.getTalkCycleStreamLive()

    override fun getTextCycleStreamLive(): LiveData<CycleEntity?> = localDataSource.getTextCycleStreamLive()

    override fun updateDataCycle(cycle: CycleEntity) = localDataSource.updateDataCycle(cycle)

    override fun updateTalkCycle(cycle: CycleEntity) = localDataSource.updateTalkCycle(cycle)

    override fun updateTextCycle(cycle: CycleEntity) = localDataSource.updateTextCycle(cycle)

    override fun getUsagesStreamLive(): LiveData<List<UsageEntity>> = localDataSource.getUsagesStreamLive()

    override fun getTextUsageStreamLive(): LiveData<UsageEntity?> = localDataSource.getTextUsageStreamLive()

    override fun getTalkUsageStreamLive(): LiveData<UsageEntity?> = localDataSource.getTalkUsageStreamLive()

    override fun updateBaseCost(changeAmount: Int) = localDataSource.updateBaseCost(changeAmount)
}