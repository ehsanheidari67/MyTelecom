package ir.ipack.ehsan.local.ipack.data.source

import androidx.lifecycle.LiveData
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import rx.Observable

interface DataSource {
    fun getBasePlanStreams(): Observable<BasePlanEntity>

    fun getDataCycleStream(): Observable<CycleEntity>
    fun getTalkCycleStream(): Observable<CycleEntity>
    fun getTextCycleStream(): Observable<CycleEntity>

    fun updateDataCycle(cycle: CycleEntity)
    fun updateTalkCycle(cycle: CycleEntity)
    fun updateTextCycle(cycle: CycleEntity)

    fun getUsagesStreamLive(): LiveData<List<UsageEntity>>

    fun updateBaseCost(changeAmount: Int)
    fun getTextUsageStreamLive(): LiveData<List<UsageEntity>>
    fun getTalkUsageStreamLive(): LiveData<List<UsageEntity>>
}