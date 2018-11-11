package ir.ipack.ehsan.local.ipack.data.source

import ir.ipack.ehsan.local.ipack.data.BasePlan
import ir.ipack.ehsan.local.ipack.data.Cycle
import ir.ipack.ehsan.local.ipack.data.source.local.LocalDataSource
import rx.Observable

class Repository private constructor(private val localDataSource: LocalDataSource) : DataSource {

    override fun getBasePlanStreams(): Observable<BasePlan> = localDataSource.getBasePlanStreams()

    override fun getDataCycleStreams(): Observable<Cycle> = localDataSource.getDataCycleStreams()
    override fun getTalkCycleStreams(): Observable<Cycle> = localDataSource.getTalkCycleStreams()
    override fun getTextCycleStreams(): Observable<Cycle> = localDataSource.getTextCycleStreams()

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