package ir.ipack.ehsan.local.ipack

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import ir.ipack.ehsan.local.ipack.data.source.Repository

abstract class BaseViewModel (
    open val context: Application
) : AndroidViewModel(context){

    abstract fun updateBaseCost(changeAmount : Int)
}