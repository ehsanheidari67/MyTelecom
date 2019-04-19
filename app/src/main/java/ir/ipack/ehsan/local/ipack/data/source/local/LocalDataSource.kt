package ir.ipack.ehsan.local.ipack.data.source.local

import androidx.lifecycle.LiveData
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.DataSource
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum

class LocalDataSource(private val dataPersistence: DataPersistence) :
    DataSource {

    override fun getTalkUsageStreamLive(): LiveData<List<UsageEntity>> {
        return talkUsageLive
    }

    override fun getTextUsageStreamLive(): LiveData<List<UsageEntity>> {
        return textUsageLive
    }

    override fun getUsagesStreamLive(): LiveData<List<UsageEntity>> {
        return appUsageLive
    }

    override fun getDataCycleStreamLive(): LiveData<List<CycleEntity>> =
        dataPersistence.getCycleByTypeLive(CycleTypeEnum.INTERNET)

    override fun getTalkCycleStreamLive(): LiveData<List<CycleEntity>> =
        dataPersistence.getCycleByTypeLive(CycleTypeEnum.TALK)

    override fun getTextCycleStreamLive(): LiveData<List<CycleEntity>> =
        dataPersistence.getCycleByTypeLive(CycleTypeEnum.TEXT)

    override fun getBasePlanStreamLive(): LiveData<List<BasePlanEntity>> =
        dataPersistence.getBasePlan()

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

    private val talkUsageLive: LiveData<List<UsageEntity>>
        get() = dataPersistence.getUsageByTypeLive(CycleTypeEnum.TALK)
    private val textUsageLive: LiveData<List<UsageEntity>>
        get() = dataPersistence.getUsageByTypeLive(CycleTypeEnum.TEXT)
    private val appUsageLive: LiveData<List<UsageEntity>>
        get() = dataPersistence.getUsageByTypeLive(CycleTypeEnum.INTERNET)
}

interface DataPersistence {
    fun getUsageByTypeLive(cycleTypeEnum: CycleTypeEnum): LiveData<List<UsageEntity>>
    fun getCycleByTypeLive(cycleTypeEnum: CycleTypeEnum): LiveData<List<CycleEntity>>
    fun setCycle(cycle: CycleEntity)
    fun getBasePlan(): LiveData<List<BasePlanEntity>>
    fun setBasePlan(basePlanEntity: BasePlanEntity)
    fun updateBasePlan(changeAmount: Int)
}