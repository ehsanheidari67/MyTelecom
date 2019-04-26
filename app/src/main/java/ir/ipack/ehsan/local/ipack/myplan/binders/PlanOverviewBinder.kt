package ir.ipack.ehsan.local.ipack.myplan.binders

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import kotlinx.android.synthetic.main.my_plan_header.view.*

class PlanOverviewBinder(context: Context, dataBinderAdapter: DataBindAdapter) :
    DataBinder<PlanOverviewBinder.PlanOverviewHolder>(dataBinderAdapter) {
    private var mDataCycle: CycleEntity? = null
    private var mTalkCycle: CycleEntity? = null
    private var mTextCycle: CycleEntity? = null

    private val resources: Resources = context.resources

    override fun bindViewHolder(holder: PlanOverviewHolder?, position: Int) {
        holder?.let {
            mDataCycle?.let { cycle ->
                it.dataUsageView.setPercentUsed(cycle.usedPercentage.toInt())
                it.dataUsageView.setBottomLeftText(setUsedVsLimit(cycle))
                it.dataUsageView.setBottomRightText(
                    String.format(
                        "%.2f",
                        cycle.usedPercentage
                    ) + resources.getString(R.string.percent_used)
                )
            }
            mTalkCycle?.let { cycle ->
                it.talkUsageView.setPercentUsed(cycle.usedPercentage.toInt())
                it.talkUsageView.setBottomLeftText(setUsedVsLimit(cycle))
                it.talkUsageView.setBottomRightText(
                    String.format(
                        "%.2f",
                        cycle.usedPercentage
                    ) + resources.getString(R.string.percent_used)
                )
            }
            mTextCycle?.let { cycle ->
                it.textUsageView.setPercentUsed(cycle.usedPercentage.toInt())
                it.textUsageView.setBottomLeftText(setUsedVsLimit(cycle))
                it.textUsageView.setBottomRightText(
                    String.format(
                        "%.2f",
                        cycle.usedPercentage
                    ) + resources.getString(R.string.percent_used)
                )
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

    fun addData(dataCycle: CycleEntity) {
        this.mDataCycle = dataCycle
        notifyBinderDataSetChanged()
    }

    fun addTalk(talkCycle: CycleEntity) {
        this.mTalkCycle = talkCycle
        notifyBinderDataSetChanged()
    }

    fun addText(textCycle: CycleEntity) {
        this.mTextCycle = textCycle
        notifyBinderDataSetChanged()
    }

    private fun setUsedVsLimit(cycle: CycleEntity): String {
        return cycle.used.toString() + "/" + cycle.limit + " " + cycle.unit
    }
}