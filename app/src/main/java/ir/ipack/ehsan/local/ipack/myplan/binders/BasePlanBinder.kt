package ir.ipack.ehsan.local.ipack.myplan.binders

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.core.extension.localizedCurrency
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import kotlinx.android.synthetic.main.base_plan_info.view.*

class BasePlanBinder(dataBindAdapter: DataBindAdapter) : DataBinder<BasePlanBinder.BasePlanHolder>(dataBindAdapter) {
    var mBasePlan: BasePlanEntity? = null
    override fun bindViewHolder(holder: BasePlanHolder?, position: Int) {
        Log.i("ETest", "bindViewHolder " + mBasePlan?.baseCost)
        mBasePlan?.let {
            holder?.baseCost?.text = it.baseCost?.localizedCurrency(false)
            holder?.addonCost?.text = it.addonCost?.localizedCurrency(false)
        }
    }

    override fun getItemCount(): Int = 1

    override fun newViewHolder(parent: ViewGroup?): BasePlanHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.base_plan_info, parent, false)
        return BasePlanHolder(view)
    }

    fun add(basePlan: BasePlanEntity) {
        mBasePlan = basePlan
        Log.i("ETest", "add " + mBasePlan?.baseCost)
        notifyBinderDataSetChanged()
    }

    class BasePlanHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val baseCost = itemView.base_cost
        val addonCost = itemView.addon_cost
    }
}