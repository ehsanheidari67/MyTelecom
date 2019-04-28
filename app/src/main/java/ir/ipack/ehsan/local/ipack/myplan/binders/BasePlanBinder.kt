package ir.ipack.ehsan.local.ipack.myplan.binders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder
import ir.ipack.ehsan.local.ipack.BR
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.core.extension.localizedCurrency
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity

class BasePlanBinder(dataBindAdapter: DataBindAdapter) : DataBinder<BasePlanBinder.BasePlanHolder>(dataBindAdapter) {
    var basePlan: BasePlanEntity? = null
    override fun bindViewHolder(holder: BasePlanHolder?, position: Int) {
        basePlan?.let {
            holder?.bind(it)
        }
    }

    override fun getItemCount(): Int = 1

    override fun newViewHolder(parent: ViewGroup?): BasePlanHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val viewDataBinding: ViewDataBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.base_plan_info, parent, false
        )
        return BasePlanHolder(viewDataBinding)
    }

    fun add(basePlan: BasePlanEntity) {
        this.basePlan = basePlan
        notifyBinderDataSetChanged()
    }

    class BasePlanHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(basePlan: BasePlanEntity) {
            binding.setVariable(BR.basePlan, basePlan)
        }
    }
}

@BindingAdapter("basePlanCost")
fun TextView.setAddonText(cost: Double) {
    text = cost.localizedCurrency(false)
}