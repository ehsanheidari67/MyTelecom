package ir.ipack.ehsan.local.ipack.utils

import android.content.Context
import android.graphics.Typeface
import java.lang.Exception
import java.util.*

object FontCache {
    private val fontCache = Hashtable<String, Typeface>()

    fun get(name: String, context: Context?): Typeface? {
        var tf = fontCache[name]
        tf?.let {
            try {
                tf = Typeface.createFromAsset(context?.assets, name)
            } catch (e: Exception) {
                return null
            }
            fontCache.put(name, tf)
        }

        return tf
    }
}