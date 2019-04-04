package ir.ipack.ehsan.local.ipack.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.utils.FontCache

class RobotoTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    private var robotoFontName: String? = null

    init {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.RobotoTextView, 0, 0)
        try {
            robotoFontName = a.getString(R.styleable.RobotoTextView_fontName)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            a.recycle()
        }

        robotoFontName?.let {
            setFont(context, it)
        } ?: run {
            robotoFontName = "Roboto-Regular.ttf"
        }
    }

    fun setFont(context: Context, robotoFont: String) {
        val font = FontCache.get(robotoFont, context)
        this.typeface = font

        font.also {}
    }
}