package ir.ipack.ehsan.local.ipack.data.source.local

import android.support.annotation.VisibleForTesting
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.BasePlan
import ir.ipack.ehsan.local.ipack.data.Cycle
import ir.ipack.ehsan.local.ipack.data.source.DataSource
import ir.ipack.ehsan.local.ipack.utils.PlanConstants
import rx.Observable
import rx.subjects.PublishSubject

class LocalDataSource private constructor() : DataSource {

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

    override fun getDataCycleStreams(): Observable<Cycle> =
        Observable.merge(Observable.just(getDataCycle()), mDataCycleStream)
    override fun getTalkCycleStreams(): Observable<Cycle> =
        Observable.merge(Observable.just(getTalkCycle()), mTalkCycleStream)
    override fun getTextCycleStreams(): Observable<Cycle> =
        Observable.merge(Observable.just(getTextCycle()), mTextCycleStream)

    override fun getBasePlanStreams(): Observable<BasePlan> =
        Observable.merge(Observable.just(mBasePlan), mBasePlanStream)

    companion object {
        private val mBasePlan = BasePlan()
        private val mBasePlanStream = PublishSubject.create<BasePlan>()

        private var mDataCycle: Cycle? = null;
        private val mDataCycleStream = PublishSubject.create<Cycle>()
        private var mTalkCycle: Cycle? = null;
        private val mTalkCycleStream = PublishSubject.create<Cycle>()
        private var mTextCycle: Cycle? = null;
        private val mTextCycleStream = PublishSubject.create<Cycle>()

        private var INSTANCE: LocalDataSource? = null

        @JvmStatic
        fun getInstance(): LocalDataSource =
            INSTANCE ?: synchronized(LocalDataSource::class.java) {
                INSTANCE ?: LocalDataSource().also {
                    INSTANCE = it
                }
            }

        @VisibleForTesting
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}