package ir.ipack.ehsan.local.ipack.myplan

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.util.Log
import ir.ipack.ehsan.local.ipack.data.BasePlan
import ir.ipack.ehsan.local.ipack.data.source.Repository
import rx.Observable

class MyPlanViewModel(
    context: Application,
    private val repository: Repository
) : AndroidViewModel(context) {

    fun getPlanStream(): Observable<BasePlan> {
        Log.i("ETest" , "ViewModel")

        return repository.getPlanStream()
    }
}