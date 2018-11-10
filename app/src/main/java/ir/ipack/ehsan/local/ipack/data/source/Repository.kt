package ir.ipack.ehsan.local.ipack.data.source

import ir.ipack.ehsan.local.ipack.data.BasePlan
import ir.ipack.ehsan.local.ipack.data.source.local.LocalDataSource
import rx.Observable

class Repository private constructor(private val localDataSource: LocalDataSource) : DataSource {

    override fun getBasePlan(): Observable<BasePlan> {
        return localDataSource.getBasePlan()
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