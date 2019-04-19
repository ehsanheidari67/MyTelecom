package ir.ipack.ehsan.local.ipack.myplan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyPlanViewModel(
    context: Application,
    private val repository: Repository
) : AndroidViewModel(context) {

    fun getPlanStreamLive() = Transformations.map(repository.getBasePlanStreamLive()) {
        it.firstOrNull()
    }

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