package ir.ipack.ehsan.local.ipack.mydata

import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.yqritc.recyclerviewmultipleviewtypesadapter.ListBindAdapter
import ir.ipack.ehsan.local.ipack.BaseViewModel
import ir.ipack.ehsan.local.ipack.commonbinders.CycleBinder
import ir.ipack.ehsan.local.ipack.commonbinders.UsageHeaderBinder
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.mydata.binders.AppUsageBinder
import ir.ipack.ehsan.local.ipack.utils.RecyclerDivider

class MyDataRecyclerAdapter(context: Context, coordinatorLayout: CoordinatorLayout, baseViewModel: BaseViewModel) : ListBindAdapter() {
    init {
        val cycleBinder = CycleBinder(context, this, coordinatorLayout, baseViewModel)
        val dataHeaderBinder = UsageHeaderBinder(this)
        val appUsageBinder = AppUsageBinder(this)
        addAllBinder(cycleBinder, dataHeaderBinder, appUsageBinder)
    }

    fun setCycle(cycle: CycleEntity) {
        getDataBinder<CycleBinder>(0).add(cycle)
    }

    fun setDividerHeader(divider: RecyclerDivider) {
        getDataBinder<UsageHeaderBinder>(1).add(divider)
    }

    fun setAppUsage(appUsages: List<UsageEntity>) {
        getDataBinder<AppUsageBinder>(2).addAll(appUsages)
    }
}