package ir.ipack.ehsan.local.ipack.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import kotlin.text.Charsets.UTF_8

// TODO: Replace with Moshi in a future PR
object JsonUtils {
    fun <T> parseJsonFile(ctx: Context, filename: String, token: TypeToken<T>): T {
        return Gson().fromJson<T>(parseJsonFile(ctx, filename), token.type)
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