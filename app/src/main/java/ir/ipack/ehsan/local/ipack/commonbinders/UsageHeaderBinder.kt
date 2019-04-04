package ir.ipack.ehsan.local.ipack.commonbinders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.utils.RecyclerDivider
import kotlinx.android.synthetic.main.data_header.view.*

class UsageHeaderBinder(dataBindAdapter: DataBindAdapter) :
    DataBinder<UsageHeaderBinder.UsageHeaderViewHolder>(dataBindAdapter) {

    private var mRecyclerDivider: RecyclerDivider? = null

    override fun bindViewHolder(holder: UsageHeaderViewHolder?, position: Int) {
        holder?.let {
            mRecyclerDivider?.let { recyclerDivider ->
                it.headerLayout.setBackgroundColor(-1)
                it.dataHeader.text = recyclerDivider.dividerText
                val bgColor = recyclerDivider.dividerBGColor
                if (bgColor != -1)
                    it.headerLayout.setBackgroundColor(bgColor)
            }
        }
    }

    override fun getItemCount(): Int = 1

    override fun newViewHolder(parent: ViewGroup?): UsageHeaderViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.data_header, parent, false)
        return UsageHeaderViewHolder(view)
    }

    class UsageHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dataHeader = itemView.divider_text_view
        val headerLayout = itemView.header
    }

    fun add(header: RecyclerDivider) {
        this.mRecyclerDivider = header
        notifyBinderDataSetChanged()
    }
}