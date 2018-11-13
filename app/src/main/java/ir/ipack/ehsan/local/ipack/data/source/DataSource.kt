package ir.ipack.ehsan.local.ipack.data.source

import android.content.Context
import ir.ipack.ehsan.local.ipack.data.BasePlan
import ir.ipack.ehsan.local.ipack.data.Cycle
import ir.ipack.ehsan.local.ipack.data.Usage
import rx.Observable

interface DataSource {
    fun getBasePlanStreams(): Observable<BasePlan>

    fun getDataCycleStream(): Observable<Cycle>
    fun getTalkCycleStream(): Observable<Cycle>
    fun getTextCycleStream(): Observable<Cycle>

    fun getUsagesStream(context: Context): Observable<Usage>
    fun getTalkUsageStream(context: Context): Observable<Usage>
    fun getTextUsageStream(context: Context): Observable<Usage>


}