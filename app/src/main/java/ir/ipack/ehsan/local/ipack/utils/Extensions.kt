package ir.ipack.ehsan.local.ipack.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import ir.ipack.ehsan.local.ipack.data.Cycle
import timber.log.Timber
import java.lang.reflect.Type
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Cycle.getUsedPercentage(): Float {
    var usedPercent = used * 100 / limit
    val df: DecimalFormat
    if (Locale.getDefault().toString() == "en_US") {
        df = DecimalFormat("#.##")
    } else {
        df = DecimalFormat("#,##")
    }
    usedPercent = java.lang.Float.valueOf(df.format(usedPercent.toDouble()))
    return usedPercent
}

fun Double.localizedCurrency(showDecimalPlaces: Boolean): String {
    val defaultFormat = NumberFormat.getCurrencyInstance()
    val decimalPlaces = if (showDecimalPlaces) 2 else 0
    defaultFormat.minimumFractionDigits = decimalPlaces
    defaultFormat.maximumFractionDigits = 2
    return defaultFormat.format(this)
}

fun <T> Moshi.jsonToList(json: String, type: Type): List<T> {
    return try {
        val listOfObjects = Types.newParameterizedType(List::class.java, type)
        val jsonAdapter = adapter<List<T>>(listOfObjects)
        jsonAdapter.fromJson(json) ?: emptyList()
    } catch (t: Throwable) {
        Timber.e(t)
        emptyList()
    }
}