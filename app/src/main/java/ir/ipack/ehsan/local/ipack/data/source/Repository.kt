package ir.ipack.ehsan.local.ipack.data.source

import androidx.lifecycle.LiveData
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.local.LocalDataSource
import rx.Observable

class Repository(private val localDataSource: LocalDataSource) : DataSource {
    override fun getBasePlanStreams(): Observable<BasePlanEntity> = localDataSource.getBasePlanStreams()

    override fun getDataCycleStream(): Observable<CycleEntity> = localDataSource.getDataCycleStream()
    override fun getTalkCycleStream(): Observable<CycleEntity> = localDataSource.getTalkCycleStream()
    override fun getTextCycleStream(): Observable<CycleEntity> = localDataSource.getTextCycleStream()

    override fun updateDataCycle(cycle: CycleEntity) {
        localDataSource.updateDataCycle(cycle)
    }

    override fun updateTalkCycle(cycle: CycleEntity) {
        localDataSource.updateTalkCycle(cycle)
    }

    override fun updateTextCycle(cycle: CycleEntity) {
        localDataSource.updateTextCycle(cycle)
    }

    override fun getUsagesStreamLive(): LiveData<List<UsageEntity>> = localDataSource.getUsagesStreamLive()

    override fun getTextUsageStreamLive(): LiveData<List<UsageEntity>> = localDataSource.getTextUsageStreamLive()

    override fun getTalkUsageStreamLive(): LiveData<List<UsageEntity>> = localDataSource.getTalkUsageStreamLive()

    override fun updateBaseCost(changeAmount: Int) = localDataSource.updateBaseCost(changeAmount)
}