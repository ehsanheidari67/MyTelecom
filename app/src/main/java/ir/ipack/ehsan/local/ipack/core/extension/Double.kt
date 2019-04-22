package ir.ipack.ehsan.local.ipack.core.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import java.text.NumberFormat

fun Double.localizedCurrency(showDecimalPlaces: Boolean): String {
    val defaultFormat = NumberFormat.getCurrencyInstance()
    val decimalPlaces = if (showDecimalPlaces) 2 else 0
    defaultFormat.minimumFractionDigits = decimalPlaces
    defaultFormat.maximumFractionDigits = 2
    return defaultFormat.format(this)
}

fun <T, R> LiveData<T>.map(mapFunction: (T) -> R): LiveData<R> = Transformations.map(this, mapFunction)