package ir.ipack.ehsan.local.ipack.data.source.local

import android.support.annotation.VisibleForTesting
import android.util.Log
import ir.ipack.ehsan.local.ipack.data.BasePlan
import ir.ipack.ehsan.local.ipack.data.source.DataSource
import rx.Observable
import rx.subjects.PublishSubject

class LocalDataSource private constructor() : DataSource {

    override fun getBasePlan(): Observable<BasePlan> = Observable.merge(Observable.just(mBasePlan), mBasePlanStream)

    companion object {
        private val mBasePlan = BasePlan()
        private val mBasePlanStream = PublishSubject.create<BasePlan>()

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