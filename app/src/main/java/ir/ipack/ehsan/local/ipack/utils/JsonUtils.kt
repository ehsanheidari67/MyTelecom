package ir.ipack.ehsan.local.ipack.utils

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.IOException
import java.lang.reflect.Type
import kotlin.text.Charsets.UTF_8

object JsonUtils {
    //TODO: Remove redundant generic in a future PR
    fun <T> parseJsonListFile(ctx: Context, filename: String, type: Type): List<T> {
        return try {
            val moshi = Moshi.Builder().build()
            val listOfObjects = Types.newParameterizedType(List::class.java, type)
            val jsonAdapter = moshi.adapter<List<T>>(listOfObjects)
            jsonAdapter.fromJson(parseJsonFile(ctx, filename) ?: "") ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun parseJsonFile(ctx: Context, filename: String): String? {
        val json: String
        try {
            val stream = ctx.assets.open(filename)
            val buffer = ByteArray(stream.available())
            stream.read(buffer)
            stream.close()
            json = String(buffer, UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return json
    }
}