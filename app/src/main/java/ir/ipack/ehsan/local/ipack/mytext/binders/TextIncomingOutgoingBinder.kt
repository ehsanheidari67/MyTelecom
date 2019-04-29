package ir.ipack.ehsan.local.ipack.mytext.binders

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

class TextIncomingOutgoingBinder(dataBindAdapter: DataBindAdapter) :
    DataBinder<TextIncomingOutgoingBinder.TextUsageViewHolder>(dataBindAdapter) {
    private var textUsage: UsageEntity? = null

    override fun bindViewHolder(holder: TextUsageViewHolder?, position: Int) {
        holder?.let {
            textUsage?.let { usage ->
                it.bind(usage)

//                ResourcesCompat.getDrawable(
//                    mResources,
//                    R.drawable.intext,
//                    null
//                )?.apply {
//                    it.incomingBar.setImageSource(this)
//                }
//
//                ResourcesCompat.getDrawable(
//                    mResources,
//                    R.drawable.outtext,
//                    null
//                )?.apply {
//                    it.outgoingBar.setImageSource(this)
//                }
            }
        }
    }

    override fun getItemCount(): Int = 1

    override fun newViewHolder(parent: ViewGroup?): TextUsageViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val viewDataBinding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.incoming_outgoing, parent, false)
        return TextUsageViewHolder(viewDataBinding)
    }

    fun add(textUsage: UsageEntity) {
        this.textUsage = textUsage
        notifyBinderDataSetChanged()
    }

    class TextUsageViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(textUsage: UsageEntity?) {
            binding.setVariable(BR.talkUsage, textUsage)
        }
    }
}