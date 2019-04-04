package ir.ipack.ehsan.local.ipack.myplan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ir.ipack.ehsan.local.ipack.data.BasePlan
import ir.ipack.ehsan.local.ipack.data.source.Repository
import rx.Observable

class MyPlanViewModel(
    context: Application,
    private val repository: Repository
) : AndroidViewModel(context) {

    fun getPlanStream(): Observable<BasePlan> = repository.getBasePlanStreams()

    fun getDataCycleStream() = repository.getDataCycleStream()
    fun getTalkCycleStream() = repository.getTalkCycleStream()
    fun getTextCycleStream() = repository.getTextCycleStream()
}