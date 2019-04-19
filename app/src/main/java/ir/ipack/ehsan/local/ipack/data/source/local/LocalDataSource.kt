package ir.ipack.ehsan.local.ipack.data.source.local

import androidx.lifecycle.LiveData
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.DataSource
import ir.ipack.ehsan.local.ipack.utils.AppExecutors
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum
import ir.ipack.ehsan.local.ipack.utils.PlanConstants
import ir.ipack.ehsan.local.ipack.utils.UnitEnum
import rx.Observable
import rx.subjects.PublishSubject

class LocalDataSource(private val dataPersistence: DataPersistence, private val appExecutors: AppExecutors) :
    DataSource {

    private val mBasePlan = BasePlanEntity()

    private val mBasePlanStream = PublishSubject.create<BasePlanEntity>()
    private var mDataCycle: CycleEntity? = null

    private val mDataCycleStream = PublishSubject.create<CycleEntity>()
    private var mTalkCycle: CycleEntity? = null
    private val mTalkCycleStream = PublishSubject.create<CycleEntity>()
    private var mTextCycle: CycleEntity? = null
    private val mTextCycleStream = PublishSubject.create<CycleEntity>()

    override fun getTalkUsageStreamLive(): LiveData<List<UsageEntity>> {
        return talkUsageLive
    }

    override fun getTextUsageStreamLive(): LiveData<List<UsageEntity>> {
        return textUsageLive
    }

    override fun getUsagesStreamLive(): LiveData<List<UsageEntity>> {
        return appUsageLive
    }

    private fun getDataCycle(): CycleEntity =
        mDataCycle ?: CycleEntity(
            CycleTypeEnum.INTERNET, UnitEnum.GB, R.drawable.data_dark_gray, PlanConstants.INITIAL_USED_DATA.toDouble(),
            PlanConstants.INITIAL_DATA_AMOUNT
        )

    private fun getTalkCycle(): CycleEntity =
        mTalkCycle ?: CycleEntity(
            CycleTypeEnum.TALK, UnitEnum.MIN, R.drawable.data_dark_gray, PlanConstants.INITIAL_USED_TALK.toDouble(),
            PlanConstants.INITIAL_TALK_AMOUNT
        )

    private fun getTextCycle(): CycleEntity =
        mTextCycle ?: CycleEntity(
            CycleTypeEnum.TEXT, UnitEnum.SMS, R.drawable.text_dark_gray, PlanConstants.INITIAL_USED_TEXT.toDouble(),
            PlanConstants.INITIAL_USED_TEXT
        )

    override fun getDataCycleStream(): Observable<CycleEntity> =
        Observable.merge(Observable.just(getDataCycle()), mDataCycleStream)

    override fun getTalkCycleStream(): Observable<CycleEntity> =
        Observable.merge(Observable.just(getTalkCycle()), mTalkCycleStream)

    override fun getTextCycleStream(): Observable<CycleEntity> =
        Observable.merge(Observable.just(getTextCycle()), mTextCycleStream)

    override fun getBasePlanStreams(): Observable<BasePlanEntity> =
        Observable.merge(Observable.just(mBasePlan), mBasePlanStream)

    override fun updateDataCycle(cycle: CycleEntity) {
        mDataCycle = cycle
        mDataCycleStream.onNext(cycle)
    }

    override fun updateTalkCycle(cycle: CycleEntity) {
        mTalkCycle = cycle
        mTalkCycleStream.onNext(cycle)
    }

    override fun updateTextCycle(cycle: CycleEntity) {
        mTextCycle = cycle
        mTextCycleStream.onNext(cycle)
    }

    override fun updateBaseCost(changeAmount: Int) {
        mBasePlan.baseCost += changeAmount
        mBasePlanStream.onNext(mBasePlan)
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
}