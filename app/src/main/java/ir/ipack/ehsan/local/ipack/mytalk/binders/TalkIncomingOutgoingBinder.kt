package ir.ipack.ehsan.local.ipack.mytalk.binders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.utils.PlanConstants
import kotlinx.android.synthetic.main.incoming_outgoing.view.*

class TalkIncomingOutgoingBinder(dataBindAdapter: DataBindAdapter) :
    DataBinder<TalkIncomingOutgoingBinder.TalkUsageViewHolder>(dataBindAdapter) {
    private var mTalkUsage: UsageEntity? = null

    override fun bindViewHolder(holder: TalkUsageViewHolder?, position: Int) {
        holder?.let {
            mTalkUsage?.let { usage ->
                it.incomingBar.setBottomRightText(usage.incoming.toString() + " " + PlanConstants.TALK_UNIT)
                it.outgoingBar.setBottomRightText(usage.outgoing.toString() + " " + PlanConstants.TALK_UNIT)
                if (usage.total != null && usage.incoming != null && usage.outgoing != null) {
                    it.incomingBar.setPercentUsed(usage.incoming * 100 / usage.total)
                    it.outgoingBar.setPercentUsed(usage.outgoing * 100 / usage.total)
                }
            }
        }
    }

    override fun getItemCount(): Int = 1

    override fun newViewHolder(parent: ViewGroup?): TalkUsageViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.incoming_outgoing, parent, false)
        return TalkUsageViewHolder(view)
    }

    fun add(talkUsage: UsageEntity) {
        mTalkUsage = talkUsage
        notifyBinderDataSetChanged()
    }

    class TalkUsageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val incomingBar = itemView.incomingBar
        val outgoingBar = itemView.outgoingBar
    }
}