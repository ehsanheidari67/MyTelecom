package ir.ipack.ehsan.local.ipack.mytext.binders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.utils.PlanConstants
import kotlinx.android.synthetic.main.incoming_outgoing.view.*

class TextIncomingOutgoingBinder(context: Context, dataBindAdapter: DataBindAdapter) :
    DataBinder<TextIncomingOutgoingBinder.TextUsageViewHolder>(dataBindAdapter) {
    private var mTextUsage: UsageEntity? = null
    private val mResources = context.resources

    override fun bindViewHolder(holder: TextUsageViewHolder?, position: Int) {
        holder?.let {
            mTextUsage?.let { usage ->
                ResourcesCompat.getDrawable(
                    mResources,
                    R.drawable.intext,
                    null
                )?.apply {
                    it.incomingBar.setImageSource(this)
                }

                ResourcesCompat.getDrawable(
                    mResources,
                    R.drawable.outtext,
                    null
                )?.apply {
                    it.outgoingBar.setImageSource(this)
                }

                it.incomingBar.setBottomRightText(usage.incoming.toString() + " " + PlanConstants.TEXT_UNIT)
                it.outgoingBar.setBottomRightText(usage.outgoing.toString() + " " + PlanConstants.TEXT_UNIT)

                if (usage.total != null && usage.incoming != null && usage.outgoing != null) {
                    it.incomingBar.setPercentUsed(usage.incoming * 100 / usage.total)
                    it.outgoingBar.setPercentUsed(usage.outgoing * 100 / usage.total)
                }
            }
        }
    }

    override fun getItemCount(): Int = 1

    override fun newViewHolder(parent: ViewGroup?): TextUsageViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.incoming_outgoing, parent, false)
        return TextUsageViewHolder(view)
    }

    fun add(textUsage: UsageEntity) {
        mTextUsage = textUsage
        notifyBinderDataSetChanged()
    }

    class TextUsageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val incomingBar = itemView.incomingBar
        val outgoingBar = itemView.outgoingBar
    }
}