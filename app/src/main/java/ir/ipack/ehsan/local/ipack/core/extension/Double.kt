package ir.ipack.ehsan.local.ipack.core.extension

import java.text.NumberFormat

fun Double.localizedCurrency(showDecimalPlaces: Boolean): String {
    val defaultFormat = NumberFormat.getCurrencyInstance()
    val decimalPlaces = if (showDecimalPlaces) 2 else 0
    defaultFormat.minimumFractionDigits = decimalPlaces
    defaultFormat.maximumFractionDigits = 2
    return defaultFormat.format(this)
}