package ir.ipack.ehsan.local.ipack.mydata.binders

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.utils.PlanConstants
import kotlinx.android.synthetic.main.telco_usage_view.view.*

class AppUsageBinder(context: Context, dataBindAdapter: DataBindAdapter) :
    DataBinder<AppUsageBinder.AppUsageViewHolder>(dataBindAdapter) {

    private var mAppUsages: List<UsageEntity> = ArrayList()
    private var mContext: Context = context
    private var mResources: Resources = context.resources

    override fun bindViewHolder(holder: AppUsageViewHolder?, position: Int) {
        val dataUsage = mAppUsages[position]
        holder?.let {
            it.usageProgressBar.visibility = View.VISIBLE
            it.usageBottomLeftText.textSize = 12f
            it.usageBottomLeftText.setTextColor(mResources.getColor(R.color.light_gray))
            it.usageBottomLeftText.setFont(mContext, mResources.getString(R.string.roboto_regular))
            it.usageBottomLeftText.text = dataUsage.used.toString() + " " + PlanConstants.DATA_UNIT
            it.usageTopText.text = dataUsage.appName

            dataUsage.imageName?.let { dataUsageUsageImageName ->
                val resId = mContext.resources.getIdentifier(dataUsageUsageImageName, "drawable",
                    mContext.packageName)
                it.usageImage.setImageResource(resId)
            }

            it.usageProgressBar.progress = dataUsage.seekBarProgress

            it.usageBottomRightText.text = dataUsage.seekBarProgress.toString() + mResources.getString(R.string.percent_used)

            if (dataUsage.isUnlimited) {
                it.usageProgressBar.visibility = View.GONE
                it.usageBottomLeftText.textSize = 14f
                it.usageBottomLeftText.setTextColor(mResources.getColor(R.color.dark_gray))
                it.usageBottomLeftText.setFont(mContext, mResources.getString(R.string.roboto_medium))
                it.usageBottomRightText.text = mResources.getString(R.string.unlimited_offer)
            }
        }
    }

    override fun getItemCount(): Int = mAppUsages.size

    override fun newViewHolder(parent: ViewGroup?): AppUsageViewHolder {
        val appUsageView =
            LayoutInflater.from(parent?.context).inflate(R.layout.data_app_usage, parent, false)
        return AppUsageViewHolder(appUsageView)
    }

    fun addAll(usages: List<UsageEntity>) {
        mAppUsages = usages
        notifyBinderDataSetChanged()
    }

    class AppUsageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usageImage = itemView.usage_image
        val usageTopText = itemView.usage_top_text
        val usageBottomLeftText = itemView.bottom_left_text
        val usageBottomRightText = itemView.bottom_right_text
        val usageProgressBar = itemView.progress_bar

        init {
        }
    }
}