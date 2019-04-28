package ir.ipack.ehsan.local.ipack.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import kotlinx.android.synthetic.main.telco_usage_view.view.*

class TelecoUsageView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var percentUsed: Int = 0
    private var imageSource: Drawable? = null
    private var topText: String? = null
    private var bottomLeftText: String? = null
    private var bottomRightText: String? = null
    private var progressBarHidden: Boolean = false

    init {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.TelcoUsageView, 0, 0)
        try {
            percentUsed = a.getInteger(R.styleable.TelcoUsageView_percentUsed, 50)
            imageSource = a.getDrawable(R.styleable.TelcoUsageView_image)
            topText = a.getString(R.styleable.TelcoUsageView_topText)
            bottomLeftText = a.getString(R.styleable.TelcoUsageView_bottomLeftText)
            bottomRightText = a.getString(R.styleable.TelcoUsageView_bottomRightText)
            progressBarHidden = a.getBoolean(R.styleable.TelcoUsageView_progressBarHidden, false)
        } finally {
            a.recycle()
        }
        init()
    }

    private fun init() {
        val view = View.inflate(context, R.layout.telco_usage_view, this)

        setProgressBarUI()
        setUsageImageUI()
        setTextView(usage_top_text, topText)
        setTextView(bottom_left_text, bottomLeftText)
        setTextView(bottom_right_text, bottomRightText)
    }

    private fun setProgressBarUI() {
        if (progressBarHidden) {
            progress_bar.visibility = View.GONE
        } else {
            progress_bar.progress = percentUsed
            progress_bar.visibility = View.VISIBLE
        }
    }

    private fun setUsageImageUI() {
        if (imageSource != null) {
            usage_image.setImageDrawable(imageSource)
            usage_image.visibility = View.VISIBLE
        } else {
            usage_image.visibility = View.GONE
        }
    }

    private fun setTextView(textView: TextView, text: String?) {
        if (text != null && text.isNotEmpty()) {
            textView.text = text
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }
    }

    fun setPercentUsed(percentUsed: Int) {
        this.percentUsed = percentUsed
        setProgressBarUI()
    }


    fun setImageSource(imageSource: Drawable) {
        this.imageSource = imageSource
        setUsageImageUI()
    }

    fun setBottomLeftText(text: String) {
        this.bottomLeftText = text
        setTextView(bottom_left_text, text)
    }

    fun setBottomRightText(text: String) {
        this.bottomRightText = text
        setTextView(bottom_right_text, text)
    }
}

@BindingAdapter("percentUsed")
fun TelecoUsageView.setPercentUsed1(percentUsed: Double) {
    setPercentUsed(percentUsed.toInt())
}

@BindingAdapter("cycle")
fun TelecoUsageView.setCycle(cycle:CycleEntity?){
    cycle?.let {
        setPercentUsed(it.usedPercentage.toInt())
        setBottomLeftText(setUsedVsLimit(it))
        setBottomRightText(
            String.format(
                "%.2f",
                cycle.usedPercentage
            ) + resources.getString(R.string.percent_used)
        )
    }

}

private fun setUsedVsLimit(cycle: CycleEntity): String {
    return cycle.used.toString() + "/" + cycle.limit + " " + cycle.unit
}
