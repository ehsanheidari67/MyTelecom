package ir.ipack.ehsan.local.ipack.data.source

import androidx.lifecycle.LiveData
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity

interface DataSource {
    fun getDataCycleStreamLive(): LiveData<CycleEntity?>
    fun getTalkCycleStreamLive(): LiveData<CycleEntity?>
    fun getTextCycleStreamLive(): LiveData<CycleEntity?>

    fun updateDataCycle(cycle: CycleEntity)
    fun updateTalkCycle(cycle: CycleEntity)
    fun updateTextCycle(cycle: CycleEntity)

    fun getUsagesStreamLive(): LiveData<List<UsageEntity>>

    fun updateBaseCost(changeAmount: Int)

    fun getTextUsageStreamLive(): LiveData<UsageEntity?>
    fun getTalkUsageStreamLive(): LiveData<UsageEntity?>
    fun getBasePlanStreamLive(): LiveData<BasePlanEntity?>
}