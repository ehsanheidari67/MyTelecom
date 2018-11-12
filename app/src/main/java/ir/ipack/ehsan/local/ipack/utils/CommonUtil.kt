package ir.ipack.ehsan.local.ipack.utils

import ir.ipack.ehsan.local.ipack.data.Cycle
import java.text.DecimalFormat
import java.util.*

object CommonUtil {
    fun getUsedPercentage(currentCycle: Cycle): Float {
        var usedPercent = currentCycle.used * 100 / currentCycle.limit
        val df: DecimalFormat
        if (Locale.getDefault().toString() == "en_US") {
            df = DecimalFormat("#.##")
        } else {
            df = DecimalFormat("#,##")
        }
        usedPercent = java.lang.Float.valueOf(df.format(usedPercent.toDouble()))
        return usedPercent
    }

}