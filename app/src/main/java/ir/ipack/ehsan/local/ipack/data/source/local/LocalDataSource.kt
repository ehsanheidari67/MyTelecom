package ir.ipack.ehsan.local.ipack.data.source.local

import android.content.Context
import com.squareup.moshi.Moshi
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.BasePlan
import ir.ipack.ehsan.local.ipack.data.Cycle
import ir.ipack.ehsan.local.ipack.data.Usage
import ir.ipack.ehsan.local.ipack.data.source.DataSource
import ir.ipack.ehsan.local.ipack.utils.AppAssets
import ir.ipack.ehsan.local.ipack.utils.PlanConstants
import ir.ipack.ehsan.local.ipack.utils.jsonToList
import rx.Observable
import rx.subjects.PublishSubject
import timber.log.Timber

class LocalDataSource(private val appAssets: AppAssets) : DataSource {
    private val mBasePlan = BasePlan()

    private val mBasePlanStream = PublishSubject.create<BasePlan>()
    private var mDataCycle: Cycle? = null

    private val mDataCycleStream = PublishSubject.create<Cycle>()
    private var mTalkCycle: Cycle? = null
    private val mTalkCycleStream = PublishSubject.create<Cycle>()
    private var mTextCycle: Cycle? = null
    private val mTextCycleStream = PublishSubject.create<Cycle>()

    private var mAppUsages: List<Usage>? = null
    private var mTalkUsage: Usage? = null
    private var mTextUsage: Usage? = null

    override fun getUsagesStream(context: Context): Observable<Usage> = Observable.from(createUsages(context))

    override fun getTalkUsageStream(context: Context): Observable<Usage> = Observable.just(createTalkUsage(context))

    override fun getTextUsageStream(context: Context): Observable<Usage> = Observable.just(createTextUsage(context))

    private fun getDataCycle(): Cycle =
        mDataCycle ?: Cycle(
            R.drawable.data_dark_gray, PlanConstants.INITIAL_USED_DATA.toFloat(), PlanConstants.INITIAL_DATA_AMOUNT,
            PlanConstants.DATA_UNIT, PlanConstants.DATA
        )

    private fun getTalkCycle(): Cycle =
        mTalkCycle ?: Cycle(
            R.drawable.talk_dark_gray, PlanConstants.INITIAL_USED_TALK.toFloat(), PlanConstants.INITIAL_TALK_AMOUNT,
            PlanConstants.TALK_UNIT, PlanConstants.TALK
        )

    private fun getTextCycle(): Cycle =
        mTextCycle ?: Cycle(
            R.drawable.text_dark_gray, PlanConstants.INITIAL_USED_TEXT.toFloat(), PlanConstants.INITIAL_TEXT_AMOUNT,
            PlanConstants.TEXT_UNIT, PlanConstants.TEXT
        )

    override fun getDataCycleStream(): Observable<Cycle> =
        Observable.merge(Observable.just(getDataCycle()), mDataCycleStream)

    override fun getTalkCycleStream(): Observable<Cycle> =
        Observable.merge(Observable.just(getTalkCycle()), mTalkCycleStream)

    override fun getTextCycleStream(): Observable<Cycle> =
        Observable.merge(Observable.just(getTextCycle()), mTextCycleStream)

    override fun getBasePlanStreams(): Observable<BasePlan> =
        Observable.merge(Observable.just(mBasePlan), mBasePlanStream)

    override fun updateDataCycle(cycle: Cycle) {
        mDataCycle = cycle
        mDataCycleStream.onNext(cycle)
    }

    override fun updateTalkCycle(cycle: Cycle) {
        mTalkCycle = cycle
        mTalkCycleStream.onNext(cycle)
    }

    override fun updateTextCycle(cycle: Cycle) {
        mTextCycle = cycle
        mTextCycleStream.onNext(cycle)
    }

    override fun updateBaseCost(changeAmount: Int) {
        mBasePlan.baseCost += changeAmount
        mBasePlanStream.onNext(mBasePlan)
    }

    private fun createUsages(context: Context): List<Usage>? {
        mAppUsages?.let {
            return it
        }

        val initialUsages = appUsage

        for (usage in initialUsages) {
            val resId = context.resources.getIdentifier(usage.imageName, "drawable", context.packageName)
            usage.usageImage = resId
            usage.seekBarProgress = (usage.used * 100 / usage.limit).toInt()
        }
        mAppUsages = initialUsages

        return mAppUsages
    }

    private fun createTalkUsage(context: Context): Usage? {

        mTalkUsage?.let {
            return mTalkUsage
        }

        val initTalkIO = talkUsage
        mTalkUsage = initTalkIO[0]

        return mTalkUsage
    }

    private fun createTextUsage(context: Context): Usage? {
        mTextUsage?.let {
            return mTextUsage
        }

        val initTextIO = textUsage
        mTextUsage = initTextIO[0]

        return mTextUsage
    }

    private val talkUsage: List<Usage>
        get() = readUsageFromJson("talk_io.json")
    private val textUsage: List<Usage>
        get() = readUsageFromJson("text_io.json")
    private val appUsage: List<Usage>
        get() = readUsageFromJson("app_usages.json")

    private fun readUsageFromJson(filename: String): List<Usage> {
        return try {
            with(Moshi.Builder().build()) {
                jsonToList(appAssets.readAsString(appAssets.getFile(filename)), Usage::class.java)
            }
        } catch (t: Throwable) {
            Timber.e(t)
            emptyList()
        }
    }
}