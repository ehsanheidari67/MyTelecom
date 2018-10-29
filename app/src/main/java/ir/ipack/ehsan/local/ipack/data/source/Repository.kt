package ir.ipack.ehsan.local.ipack.data.source

import ir.ipack.ehsan.local.ipack.data.BasePlan
import ir.ipack.ehsan.local.ipack.data.source.local.LocalDataSource
import rx.Observable

class Repository(private val localDataSource: LocalDataSource) {

    fun getPlanStream(): Observable<BasePlan> {
        return localDataSource.getBasePlanStream()
    }

    companion object {
        private var INSTANCE: Repository? = null

        @JvmStatic
        fun getInstance(localDataSource: LocalDataSource): Repository {
            return INSTANCE ?: synchronized(Repository::class.java) {
                INSTANCE ?: Repository(localDataSource).also { INSTANCE = it }
            }
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}