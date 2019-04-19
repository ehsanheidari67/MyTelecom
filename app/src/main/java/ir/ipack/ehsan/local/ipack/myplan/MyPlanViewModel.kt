package ir.ipack.ehsan.local.ipack.myplan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.source.Repository
import rx.Observable

class MyPlanViewModel(
    context: Application,
    private val repository: Repository
) : AndroidViewModel(context) {

    fun getPlanStream(): Observable<BasePlanEntity> = repository.getBasePlanStreams()

    fun getDataCycleStreamLive() = Transformations.map(repository.getDataCycleStreamLive()) {
        it.firstOrNull()
    }

    fun getTalkCycleStreamLive() = Transformations.map(repository.getTalkCycleStreamLive()) {
        it.firstOrNull()
    }

    fun getTextCycleStreamLive() = Transformations.map(repository.getTextCycleStreamLive()) {
        it.firstOrNull()
    }
}