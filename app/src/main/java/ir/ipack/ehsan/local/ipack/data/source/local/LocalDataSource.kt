package ir.ipack.ehsan.local.ipack.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.DataSource
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum

class LocalDataSource(private val dataPersistence: DataPersistence) :
    DataSource {

    override fun getTalkUsageStreamLive(): LiveData<UsageEntity> {
        return talkUsageLive
    }

    override fun getTextUsageStreamLive(): LiveData<UsageEntity> {
        return textUsageLive
    }

    override fun getUsagesStreamLive(): LiveData<List<UsageEntity>> {
        return appUsageLive
    }

    override fun getDataCycleStreamLive(): LiveData<CycleEntity> =
        Transformations.map(dataPersistence.getCycleByTypeLive(CycleTypeEnum.INTERNET)) {
            it.firstOrNull()
        }

    override fun getTalkCycleStreamLive(): LiveData<CycleEntity> =
        Transformations.map(dataPersistence.getCycleByTypeLive(CycleTypeEnum.TALK)) {
            it.firstOrNull()
        }

    override fun getTextCycleStreamLive(): LiveData<CycleEntity> =
        Transformations.map(dataPersistence.getCycleByTypeLive(CycleTypeEnum.TEXT)) {
            it.firstOrNull()
        }

    override fun getBasePlanStreamLive(): LiveData<BasePlanEntity> =
        Transformations.map(dataPersistence.getBasePlan()) {
            it.firstOrNull()
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

    private val talkUsageLive: LiveData<UsageEntity>
        get() = Transformations.map(dataPersistence.getUsageByTypeLive(CycleTypeEnum.TALK)) {
            it.firstOrNull()
        }
    private val textUsageLive: LiveData<UsageEntity>
        get() = Transformations.map(dataPersistence.getUsageByTypeLive(CycleTypeEnum.TEXT)) {
            it.firstOrNull()
        }
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