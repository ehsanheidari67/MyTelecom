package ir.ipack.ehsan.local.ipack

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel(
    open val context: Application
) : AndroidViewModel(context) {

    abstract fun updateBaseCost(changeAmount: Int)
}