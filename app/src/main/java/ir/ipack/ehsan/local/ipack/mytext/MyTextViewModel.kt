package ir.ipack.ehsan.local.ipack.mytext

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyTextViewModel (
    val context: Application,
    private val repository: Repository
) : AndroidViewModel(context) {

    fun getTextCycleStream() = repository.getTextCycleStream()

    fun getTextUsageStream() = repository.getTextUsageStream(context)

}