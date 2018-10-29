package ir.ipack.ehsan.local.ipack.data.source.local

import android.support.annotation.VisibleForTesting
import android.util.Log
import ir.ipack.ehsan.local.ipack.data.BasePlan
import ir.ipack.ehsan.local.ipack.utils.AppExecutors
import rx.Observable
import rx.subjects.PublishSubject

class LocalDataSource private constructor() {

    fun getBasePlanStream(): Observable<BasePlan> = Observable.merge(Observable.just(mBasePlan), mBasePlanStream)
        .also {
            Log.i("ETest", "LocalDataSource")
        }

    companion object {
        private val mBasePlan = BasePlan()
        private val mBasePlanStream = PublishSubject.create<BasePlan>()

        private var INSTANCE: LocalDataSource? = null

        @JvmStatic
        fun getInstance(): LocalDataSource {
            if (INSTANCE == null) {
                synchronized(LocalDataSource::javaClass) {
                    INSTANCE = LocalDataSource()
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

}