package ir.ipack.ehsan.local.ipack.mydata

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyDataViewModel(
    val context: Application,
    private val repository: Repository
) : AndroidViewModel(context) {

    fun getDataCycleStream() = repository.getDataCycleStream()

    fun getUsagesStream() = repository.getUsagesStream(context)
    fun getTalkUsageStream() = repository.getTalkUsageStream(context)
    fun getTextUsageStream() = repository.getTextUsageStream(context)
}