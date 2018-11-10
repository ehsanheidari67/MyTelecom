package ir.ipack.ehsan.local.ipack.data

import ir.ipack.ehsan.local.ipack.utils.PlanConstants

data class BasePlan(
    var baseCost: Double = PlanConstants.INITIAL_BASE_COST.toDouble(),
    var addonCost: Double = PlanConstants.INITIAL_ADDON_COST.toDouble()
) {

    val totalCost: Double
        get() = baseCost + addonCost
}


//Todo change type type
data class Cycle(
    val cycleImage: Int,
    val used: Float,
    val limit: Int,
    val unit: String,
    val type: Int
)

data class Offer(
    val title: String,
    val body: String,
    val termsConditions: String,
    val cardIcon: Int,
    val cost: Double,
    val type: Int,
    val icon: String,
    val isAppOffer: Boolean,
    val isUnlimited: Boolean,
    val affectsBaseCost: Boolean,
    val isShareCard: Boolean,
    val amountAddedToCycle: Int,
    val appName: String,
    val usage: Float
)

data class Usage(
    val total: Int,
    val appName: String,
    val usageImage: Int,
    val seekBarProgress: Int,
    val usedData: String,
    val used: Double,
    val limit: Int,
    val isUnlimited: Boolean,
    val type: Int,
    val incoming: Int,
    val outgoing: Int,
    val imageName: String
)

