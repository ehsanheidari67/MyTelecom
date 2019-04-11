package ir.ipack.ehsan.local.ipack.data.source.local

import android.content.Context
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.data.source.DataSource
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum
import ir.ipack.ehsan.local.ipack.utils.PlanConstants
import ir.ipack.ehsan.local.ipack.utils.UnitEnum
import rx.Observable
import rx.subjects.PublishSubject

class LocalDataSource(private val dataPersistence: DataPersistence) : DataSource {
    private val mBasePlan = BasePlanEntity()

    private val mBasePlanStream = PublishSubject.create<BasePlanEntity>()
    private var mDataCycle: CycleEntity? = null

    private val mDataCycleStream = PublishSubject.create<CycleEntity>()
    private var mTalkCycle: CycleEntity? = null
    private val mTalkCycleStream = PublishSubject.create<CycleEntity>()
    private var mTextCycle: CycleEntity? = null
    private val mTextCycleStream = PublishSubject.create<CycleEntity>()

    private var mAppUsages: List<UsageEntity>? = null
    private var mTalkUsage: UsageEntity? = null
    private var mTextUsage: UsageEntity? = null

    override fun getUsagesStream(context: Context): Observable<UsageEntity> = Observable.from(createUsages(context))

    override fun getTalkUsageStream(): Observable<UsageEntity> = Observable.just(createTalkUsage())

    override fun getTextUsageStream(): Observable<UsageEntity> = Observable.just(createTextUsage())

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

    private fun createUsages(context: Context): List<UsageEntity>? =
        mAppUsages ?: run {
            appUsage.map { usage ->
                usage.apply {
                    val resId = context.resources.getIdentifier(usage.imageName, "drawable", context.packageName)
                    usage.usageImage = resId
                }
            }.also {
                mAppUsages = it
            }
        }

    private fun createTalkUsage(): UsageEntity? =
        mTalkUsage ?: run {
            talkUsage.firstOrNull().also {
                mTalkUsage = it
            }
        }

    private fun createTextUsage(): UsageEntity? =
        mTextUsage ?: run {
            textUsage.firstOrNull().also {
                mTextUsage = it
            }
        }

    private val talkUsage: List<UsageEntity>
        get() = dataPersistence.getUsageByType(CycleTypeEnum.TALK)
    private val textUsage: List<UsageEntity>
        get() = dataPersistence.getUsageByType(CycleTypeEnum.TEXT)
    private val appUsage: List<UsageEntity>
        get() = dataPersistence.getUsageByType(CycleTypeEnum.INTERNET)
}

interface DataPersistence {
    fun getUsageByType(cycleTypeEnum: CycleTypeEnum): List<UsageEntity>
}