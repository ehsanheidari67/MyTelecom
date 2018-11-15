package ir.ipack.ehsan.local.ipack.mytalk

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import ir.ipack.ehsan.local.ipack.data.source.Repository

class MyTalkViewModel (
    val context: Application,
    private val repository: Repository
) : AndroidViewModel(context) {

    fun getTalkCycleStream() = repository.getTalkCycleStream()

    fun getTalkUsageStream() = repository.getTalkUsageStream(context)

}