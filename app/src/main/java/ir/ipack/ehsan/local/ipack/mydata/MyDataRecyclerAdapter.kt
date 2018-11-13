package ir.ipack.ehsan.local.ipack.mydata

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import com.yqritc.recyclerviewmultipleviewtypesadapter.ListBindAdapter
import ir.ipack.ehsan.local.ipack.commonbinders.CycleBinder
import ir.ipack.ehsan.local.ipack.commonbinders.UsageHeaderBinder
import ir.ipack.ehsan.local.ipack.data.Cycle
import ir.ipack.ehsan.local.ipack.data.Usage
import ir.ipack.ehsan.local.ipack.mydata.binders.AppUsageBinder
import ir.ipack.ehsan.local.ipack.utils.RecyclerDivider

class MyDataRecyclerAdapter(context: Context, coordinatorLayout: CoordinatorLayout) : ListBindAdapter() {
    init {
        val cycleBinder = CycleBinder(context, this, coordinatorLayout)
        val dataHeaderBinder = UsageHeaderBinder(this)
        val appUsageBinder = AppUsageBinder(context, this)
        addAllBinder(cycleBinder, dataHeaderBinder, appUsageBinder)
    }

    fun setCycle(cycle: Cycle) {
        getDataBinder<CycleBinder>(0).add(cycle)
    }

    fun setDividerHeader(divider: RecyclerDivider){
        getDataBinder<UsageHeaderBinder>(1).add(divider)
    }

    fun setAppUsage(appUsages: List<Usage>) {
        getDataBinder<AppUsageBinder>(2).addAll(appUsages)
    }

}