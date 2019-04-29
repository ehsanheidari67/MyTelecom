package ir.ipack.ehsan.local.ipack.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.utils.CycleTypeEnum
import ir.ipack.ehsan.local.ipack.utils.PlanConstants
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


    fun setImageSource(imageSource: Drawable?) {
        this.imageSource = imageSource
        setUsageImageUI()
    }

    fun setImageSource(cycleImageResource: Int) {

        usage_image.setImageResource(cycleImageResource)

    }

    fun setBottomLeftText(text: String) {
        this.bottomLeftText = text
        setTextView(bottom_left_text, text)
    }

    fun setBottomRightText(text: String) {
        this.bottomRightText = text
        setTextView(bottom_right_text, text)
    }

    fun setTopText(text: String?) {
        this.topText = text
        setTextView(usage_top_text, text)
    }
}

@BindingAdapter("cycle")
fun TelecoUsageView.setCycle(cycle: CycleEntity?) {
    cycle?.let {
        setPercentUsed(it.usedPercentage.toInt())
        setBottomLeftText(setUsedVsLimit(it))
        setBottomRightText(
            String.format(
                "%.2f",
                cycle.usedPercentage
            ) + resources.getString(R.string.percent_used)
        )

        setImageSource(
            ContextCompat.getDrawable(
                context,
                when (cycle.type) {
                    CycleTypeEnum.TEXT -> R.drawable.text_dark_gray
                    CycleTypeEnum.TALK -> R.drawable.talk_dark_gray
                    else -> R.drawable.data_dark_gray
                }
            )
        )

    }
}

@BindingAdapter("appUsage")
fun TelecoUsageView.setAppUsage(usageEntity: UsageEntity?) {
    usageEntity?.let {
        progress_bar.visibility = View.VISIBLE
        bottom_left_text.textSize = 12f
        bottom_left_text.setTextColor(resources.getColor(R.color.light_gray))
        bottom_left_text.setFont(context, resources.getString(R.string.roboto_regular))
        setBottomLeftText(it.used.toString() + " " + PlanConstants.DATA_UNIT)
        setTopText(it.appName)

        it.imageName?.let { dataUsageUsageImageName ->
            val resId = context.resources.getIdentifier(
                dataUsageUsageImageName, "drawable",
                context.packageName
            )
            usage_image.setImageResource(resId)
        }

        progress_bar.progress = it.seekBarProgress

        setBottomRightText(it.seekBarProgress.toString() + resources.getString(R.string.percent_used))

        if (it.isUnlimited) {
            progress_bar.visibility = View.GONE
            bottom_left_text.textSize = 14f
            bottom_left_text.setTextColor(resources.getColor(R.color.dark_gray))
            bottom_left_text.setFont(context, resources.getString(R.string.roboto_medium))
            bottom_right_text.text = resources.getString(R.string.unlimited_offer)
        }
    }
}

@BindingAdapter("talkUsageIncoming")
fun TelecoUsageView.setIncomingTalkUsage(usageEntity: UsageEntity?) {
    usageEntity?.let {
        setBottomRightText(it.incoming.toString() + " " + PlanConstants.TALK_UNIT)
        if (it.total != null && it.incoming != null && it.outgoing != null) {
            setPercentUsed(it.incoming * 100 / it.total)
        }
    }

}

@BindingAdapter("talkUsageOutgoing")
fun TelecoUsageView.setOutgoingTalkUsage(usageEntity: UsageEntity?) {
    usageEntity?.let {
        setBottomRightText(it.outgoing.toString() + " " + PlanConstants.TALK_UNIT)
        if (it.total != null && it.incoming != null && it.outgoing != null) {
            setPercentUsed(it.outgoing * 100 / it.total)
        }
    }
}

private fun setUsedVsLimit(cycle: CycleEntity): String {
    return cycle.used.toString() + "/" + cycle.limit + " " + cycle.unit
}
