package ir.ipack.ehsan.local.ipack.data

import android.util.Log
import ir.ipack.ehsan.local.ipack.utils.PlanConstants

data class BasePlan(var baseCost: Double, var addonCost: Double) {
    constructor() : this(PlanConstants.INITIAL_BASE_COST.toDouble(), PlanConstants.INITIAL_ADDON_COST.toDouble()){
        Log.i("ETest" , PlanConstants.INITIAL_BASE_COST.toString())
    }

    val totalCost: Double
        get() = baseCost + addonCost
}