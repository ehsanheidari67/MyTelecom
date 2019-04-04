package ir.ipack.ehsan.local.ipack.utils

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

object JsonUtils {
    //TODO: Remove redundant generic in a future PR
    fun <T> parseJsonListFile(ctx: Context, filename: String, type: Type): List<T> {
        return try {
            val moshi = Moshi.Builder().build()
            val listOfObjects = Types.newParameterizedType(List::class.java, type)
            val jsonAdapter = moshi.adapter<List<T>>(listOfObjects)
            val appAssets = AppAssets(ctx)
            val assetFile = appAssets.getFile(filename)
            jsonAdapter.fromJson(appAssets.readAsString(assetFile)) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}