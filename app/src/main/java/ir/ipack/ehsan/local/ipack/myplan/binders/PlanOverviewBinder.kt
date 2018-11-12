package ir.ipack.ehsan.local.ipack.myplan.binders

import android.content.Context
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.Cycle
import ir.ipack.ehsan.local.ipack.utils.CommonUtil
import kotlinx.android.synthetic.main.my_plan_header.view.*

class PlanOverviewBinder(context: Context, dataBinderAdapter: DataBindAdapter) :
    DataBinder<PlanOverviewBinder.PlanOverviewHolder>(dataBinderAdapter) {
    private var mDataCycle: Cycle? = null
    private var mTalkCycle: Cycle? = null
    private var mTextCycle: Cycle? = null

    private val resources: Resources = context.resources

    override fun bindViewHolder(holder: PlanOverviewHolder?, position: Int) {
        holder?.let {
            mDataCycle?.let { cycle ->
                it.dataUsageView.setPercentUsed(CommonUtil.getUsedPercentage(cycle).toInt())
                it.dataUsageView.setBottomLeftText(setUsedVsLimit(cycle))
                it.dataUsageView.setBottomRightText(CommonUtil.getUsedPercentage(cycle).toString() + resources.getString(R.string.percent_used))
            }
            mTalkCycle?.let { cycle ->
                it.talkUsageView.setPercentUsed(CommonUtil.getUsedPercentage(cycle).toInt())
                it.talkUsageView.setBottomLeftText(setUsedVsLimit(cycle))
                it.talkUsageView.setBottomRightText(CommonUtil.getUsedPercentage(cycle).toString() + resources.getString(R.string.percent_used))
            }
            mTextCycle?.let { cycle ->
                it.textUsageView.setPercentUsed(CommonUtil.getUsedPercentage(cycle).toInt())
                it.textUsageView.setBottomLeftText(setUsedVsLimit(cycle))
                it.textUsageView.setBottomRightText(CommonUtil.getUsedPercentage(cycle).toString() + resources.getString(R.string.percent_used))
            }

        }

    }

    override fun getItemCount(): Int = 1

    override fun newViewHolder(parent: ViewGroup?): PlanOverviewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.my_plan_header, parent, false)
        return PlanOverviewHolder(view)
    }

    class PlanOverviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dataUsageView = itemView.dataUsageView
        val talkUsageView = itemView.talkUsageView
        val textUsageView = itemView.textUsageView
    }

    fun addData(dataCycle: Cycle) {
        this.mDataCycle = dataCycle
        notifyBinderDataSetChanged()
    }

    fun addTalk(talkCycle: Cycle) {
        this.mTalkCycle = talkCycle
        notifyBinderDataSetChanged()
    }

    fun addText(textCycle: Cycle) {
        this.mTextCycle = textCycle
        notifyBinderDataSetChanged()
    }

    private fun setUsedVsLimit(cycle: Cycle): String {
        return cycle.used.toString() + "/" + cycle.limit + " " + cycle.unit
    }


}