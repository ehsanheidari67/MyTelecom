package ir.ipack.ehsan.local.ipack.myplan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyPlanViewModel(
    context: Application,
    private val repository: Repository
) : AndroidViewModel(context) {

    fun getPlanStreamLive() = repository.getBasePlanStreamLive()

    fun getDataCycleStreamLive() = repository.getDataCycleStreamLive()

    fun getTalkCycleStreamLive() = repository.getTalkCycleStreamLive()

    fun getTextCycleStreamLive() = repository.getTextCycleStreamLive()
}