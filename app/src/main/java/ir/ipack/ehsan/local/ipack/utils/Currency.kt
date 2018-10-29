package ir.ipack.ehsan.local.ipack.utils

import java.text.NumberFormat

object Currency {
    fun localize(amount: Double, showDecimalPlaces: Boolean): String {
        val defaultFormat = NumberFormat.getCurrencyInstance()
        val decimalPlaces = if (showDecimalPlaces) 2 else 0
        defaultFormat.minimumFractionDigits = decimalPlaces
        defaultFormat.maximumFractionDigits = 2
        return defaultFormat.format(amount)
    }

}