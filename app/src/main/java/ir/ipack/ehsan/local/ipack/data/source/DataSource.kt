package ir.ipack.ehsan.local.ipack.data.source

import ir.ipack.ehsan.local.ipack.data.BasePlan
import ir.ipack.ehsan.local.ipack.data.Cycle
import rx.Observable

interface DataSource {
    fun getBasePlanStreams() : Observable<BasePlan>

    fun getDataCycleStreams(): Observable<Cycle>
    fun getTalkCycleStreams(): Observable<Cycle>
    fun getTextCycleStreams(): Observable<Cycle>
}