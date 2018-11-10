package ir.ipack.ehsan.local.ipack.data.source

import ir.ipack.ehsan.local.ipack.data.BasePlan
import rx.Observable

interface DataSource {
    fun getBasePlan() : Observable<BasePlan>
}