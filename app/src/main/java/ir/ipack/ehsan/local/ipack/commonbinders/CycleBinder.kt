package ir.ipack.ehsan.local.ipack.commonbinders

import android.content.Context
import android.content.res.Resources
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.Cycle
import ir.ipack.ehsan.local.ipack.utils.CommonUtil
import ir.ipack.ehsan.local.ipack.utils.PlanConstants
import kotlinx.android.synthetic.main.data_add_gb.view.*
import kotlinx.android.synthetic.main.telco_usage_view.view.*

class CycleBinder(context: Context, dataBindAdapter: DataBindAdapter, layout: CoordinatorLayout) :
    DataBinder<CycleBinder.CurrentCycleViewHolder>(dataBindAdapter) {

    private var mCurrentCycle: Cycle? = null

    private var DOLLARS_PER_STEP: Int = 0
    private var STEP_AMOUNT: Int = 0
    private var MAX_AMOUNT: Int = 0
    private val resources: Resources = context.resources


    override fun bindViewHolder(holder: CurrentCycleViewHolder?, position: Int) {
        holder?.let {
            mCurrentCycle?.let { cycle ->
                val usedPercent = CommonUtil.getUsedPercentage(cycle)
                it.usageImage.setImageResource(cycle.cycleImage)
                it.usageBottomLeftUsage.text = cycle.used.toString() + "/" + cycle.limit + " " + cycle.unit
                it.usageBottomRightText.text = usedPercent.toString() +
                        resources.getString(R.string.percent_used)
                it.usageProgressBar.progress = usedPercent.toInt()
                it.monthlyUsage.text = cycle.limit.toString()
                it.unit.text = cycle.unit
            }
        }
    }

    override fun getItemCount(): Int = 1

    override fun newViewHolder(parent: ViewGroup?): CurrentCycleViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.usage_current_cycle, parent, false)
        return CurrentCycleViewHolder(view)
    }

    fun add(cycle: Cycle) {
        mCurrentCycle = cycle
        DOLLARS_PER_STEP = getDollarsPerStep()
        STEP_AMOUNT = getStepAmount()
        MAX_AMOUNT = getMaxAmount()
        notifyBinderDataSetChanged()
    }

    private fun getStepAmount(): Int {

        when (mCurrentCycle!!.type) {
            PlanConstants.DATA -> return PlanConstants.DATA_STEP_AMOUNT
            PlanConstants.TALK -> return PlanConstants.TALK_STEP_AMOUNT
            PlanConstants.TEXT -> return PlanConstants.TEXT_STEP_AMOUNT
        }
        return 0
    }

    private fun getDollarsPerStep(): Int {
        when (mCurrentCycle!!.type) {
            PlanConstants.DATA -> return PlanConstants.DATA_DOLLARS_PER_STEP
            PlanConstants.TALK -> return PlanConstants.TALK_DOLLARS_PER_STEP
            PlanConstants.TEXT -> return PlanConstants.TEXT_DOLLARS_PER_STEP
        }
        return 0
    }

    private fun getMaxAmount(): Int {
        when (mCurrentCycle!!.type) {
            PlanConstants.DATA -> return PlanConstants.DATA_MAX_AMOUNT
            PlanConstants.TALK -> return PlanConstants.TALK_MAX_AMOUNT
            PlanConstants.TEXT -> return PlanConstants.TEXT_MAX_AMOUNT
        }
        return 0
    }


    class CurrentCycleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usageImage = itemView.usage_image
        val usageBottomLeftUsage = itemView.bottom_left_text
        val usageBottomRightText = itemView.bottom_right_text
        val usageProgressBar = itemView.progress_bar
        val monthlyUsage = itemView.usage_gb
        val upArrow: ImageView = itemView.up_arrow
        val downArrow: ImageView = itemView.down_arrow
        val confirmUpdate = itemView.confirm
        val unit = itemView.usage_unit
    }
}