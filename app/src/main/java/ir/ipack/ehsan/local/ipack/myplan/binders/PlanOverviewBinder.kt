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
import kotlinx.android.synthetic.main.my_plan_header.view.*
import java.text.DecimalFormat
import java.util.*

class PlanOverviewBinder(context: Context, dataBinderAdapter: DataBindAdapter) :
    DataBinder<PlanOverviewBinder.PlanOverviewHolder>(dataBinderAdapter) {
    private var dataCycle: Cycle? = null
    private var talkCycle: Cycle? = null
    private var textCycle: Cycle? = null

    private val resources: Resources = context.resources

    override fun bindViewHolder(holder: PlanOverviewHolder?, position: Int) {
        holder?.let {
            dataCycle?.let { cycle ->
                it.dataUsageView.setPercentUsed(getUsedPercentage(cycle).toInt())
                it.dataUsageView.setBottomLeftText(setUsedVsLimit(cycle))
                it.dataUsageView.setBottomRightText(getUsedPercentage(cycle).toString() + resources.getString(R.string.percent_used))
            }
            talkCycle?.let { cycle ->
                it.talkUsageView.setPercentUsed(getUsedPercentage(cycle).toInt())
                it.talkUsageView.setBottomLeftText(setUsedVsLimit(cycle))
                it.talkUsageView.setBottomRightText(getUsedPercentage(cycle).toString() + resources.getString(R.string.percent_used))
            }
            textCycle?.let { cycle ->
                it.textUsageView.setPercentUsed(getUsedPercentage(cycle).toInt())
                it.textUsageView.setBottomLeftText(setUsedVsLimit(cycle))
                it.textUsageView.setBottomRightText(getUsedPercentage(cycle).toString() + resources.getString(R.string.percent_used))
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
        this.dataCycle = dataCycle
        notifyBinderDataSetChanged()
    }

    fun addTalk(talkCycle: Cycle) {
        this.talkCycle = talkCycle
        notifyBinderDataSetChanged()
    }

    fun addText(textCycle: Cycle) {
        this.textCycle = textCycle
        notifyBinderDataSetChanged()
    }

    private fun getUsedPercentage(currentCycle: Cycle): Float {
        var usedPercent = currentCycle.used * 100 / currentCycle.limit
        val df: DecimalFormat
        if (Locale.getDefault().toString() == "en_US") {
            df = DecimalFormat("#.##")
        } else {
            df = DecimalFormat("#,##")
        }
        usedPercent = java.lang.Float.valueOf(df.format(usedPercent.toDouble()))
        return usedPercent
    }

    private fun setUsedVsLimit(cycle: Cycle): String {
        return cycle.used.toString() + "/" + cycle.limit + " " + cycle.unit
    }


}