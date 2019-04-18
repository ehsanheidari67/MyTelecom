package ir.ipack.ehsan.local.ipack.data.source

import android.content.Context
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

    fun getUsagesStream(context: Context): Observable<UsageEntity>
    fun getTalkUsageStream(): Observable<UsageEntity>
    fun getTextUsageStream(): Observable<UsageEntity>

    fun getUsagesStreamLive(context: Context): LiveData<List<UsageEntity>>

    fun updateBaseCost(changeAmount: Int)
}