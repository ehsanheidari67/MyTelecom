package ir.ipack.ehsan.local.ipack.mydata

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyDataViewModel(
    context: Application,
    private val repository: Repository
) : AndroidViewModel(context) {

    fun getDataCycleStream() = repository.getDataCycleStreams()

}