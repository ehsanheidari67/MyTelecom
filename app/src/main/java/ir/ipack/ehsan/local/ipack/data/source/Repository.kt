package ir.ipack.ehsan.local.ipack.data.source

import android.content.Context
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

    override fun getUsagesStream(context: Context): Observable<UsageEntity> = localDataSource.getUsagesStream(context)
    override fun getTalkUsageStream(): Observable<UsageEntity> = localDataSource.getTalkUsageStream()
    override fun getTextUsageStream(): Observable<UsageEntity> = localDataSource.getTextUsageStream()

    override fun getUsagesStreamLive(context: Context): LiveData<List<UsageEntity>> =
        localDataSource.getUsagesStreamLive(context)


    override fun updateBaseCost(changeAmount: Int) = localDataSource.updateBaseCost(changeAmount)
}