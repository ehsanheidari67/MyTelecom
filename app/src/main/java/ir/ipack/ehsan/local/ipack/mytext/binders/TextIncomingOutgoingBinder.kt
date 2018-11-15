package ir.ipack.ehsan.local.ipack.mytext.binders

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.Usage
import ir.ipack.ehsan.local.ipack.utils.PlanConstants
import kotlinx.android.synthetic.main.incoming_outgoing.view.*

class TextIncomingOutgoingBinder(context: Context, dataBindAdapter: DataBindAdapter)
    : DataBinder<TextIncomingOutgoingBinder.TextUsageViewHolder>(dataBindAdapter) {
    private var mTextUsage: Usage? = null
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
            }
        }
    }

    override fun getItemCount(): Int = 1

    override fun newViewHolder(parent: ViewGroup?): TextUsageViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.incoming_outgoing, parent, false)
        return TextUsageViewHolder(view)
    }

    fun add(textUsage: Usage){
        mTextUsage = textUsage
        notifyBinderDataSetChanged()
    }

    class TextUsageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val incomingBar = itemView.incomingBar
        val outgoingBar = itemView.outgoingBar
    }
}