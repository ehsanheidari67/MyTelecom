package ir.ipack.ehsan.local.ipack.mydata.binders

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

class AppUsageBinder(dataBindAdapter: DataBindAdapter) :
    DataBinder<AppUsageBinder.AppUsageViewHolder>(dataBindAdapter) {

    private var appUsages: List<UsageEntity> = ArrayList()

    override fun bindViewHolder(holder: AppUsageViewHolder?, position: Int) {
        val dataUsage = appUsages[position]
        holder?.bind(dataUsage)
    }

    override fun getItemCount(): Int = appUsages.size

    override fun newViewHolder(parent: ViewGroup?): AppUsageViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val viewDataBinding: ViewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.data_app_usage, parent, false)
        return AppUsageViewHolder(viewDataBinding)
    }

    fun addAll(usages: List<UsageEntity>) {
        appUsages = usages
        notifyBinderDataSetChanged()
    }

    class AppUsageViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(appUsageEntity: UsageEntity?) {
            binding.setVariable(BR.appUsage, appUsageEntity)
        }
    }
}