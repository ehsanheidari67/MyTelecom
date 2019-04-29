package ir.ipack.ehsan.local.ipack.mytalk.binders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder
import ir.ipack.ehsan.local.ipack.BR
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity

class TalkIncomingOutgoingBinder(dataBindAdapter: DataBindAdapter) :
    DataBinder<TalkIncomingOutgoingBinder.TalkUsageViewHolder>(dataBindAdapter) {
    private var talkUsage: UsageEntity? = null

    override fun bindViewHolder(holder: TalkUsageViewHolder?, position: Int) {
        holder?.let {
            talkUsage?.let { usage ->
                it.bind(usage)
            }
        }
    }

    override fun getItemCount(): Int = 1

    override fun newViewHolder(parent: ViewGroup?): TalkUsageViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val viewDataBinding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.incoming_outgoing, parent, false)
        return TalkUsageViewHolder(viewDataBinding)
    }

    fun add(talkUsage: UsageEntity) {
        this.talkUsage = talkUsage
        notifyBinderDataSetChanged()
    }

    class TalkUsageViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(talkUsage: UsageEntity?) {
            binding.setVariable(BR.talkUsage, talkUsage)
        }
    }
}