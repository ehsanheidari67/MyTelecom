package ir.ipack.ehsan.local.ipack.utils

import android.content.Context
import java.io.File

class AppAssets(private val context: Context) {
    fun getFile(fileName: String): File? {
        return context.assets.list("")?.firstOrNull {
            it == fileName
        }?.let {
            File(fileName)
        }
    }

    fun readAsString(file: File): String {
        return try {
            context.assets.open(file.path).bufferedReader().use {
                it.readText()
            }
        } catch (e: Throwable) {
            ""
        }
    }
}