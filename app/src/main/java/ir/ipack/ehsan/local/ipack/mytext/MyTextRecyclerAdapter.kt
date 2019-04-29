package ir.ipack.ehsan.local.ipack.mytext

import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.yqritc.recyclerviewmultipleviewtypesadapter.ListBindAdapter
import ir.ipack.ehsan.local.ipack.BaseViewModel
import ir.ipack.ehsan.local.ipack.commonbinders.CycleBinder
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.mytext.binders.TextIncomingOutgoingBinder

class MyTextRecyclerAdapter(context: Context, coordinatorLayout: CoordinatorLayout, baseViewModel: BaseViewModel) : ListBindAdapter() {
    init {
        val cycleBinder = CycleBinder(context, this, coordinatorLayout, baseViewModel)
        val textUsageBinder = TextIncomingOutgoingBinder(this)
        addAllBinder(cycleBinder, textUsageBinder)
    }

    fun setCycle(cycle: CycleEntity) {
        getDataBinder<CycleBinder>(0).add(cycle)
    }

    fun setTextUsage(textUsage: UsageEntity) {
        getDataBinder<TextIncomingOutgoingBinder>(1).add(textUsage)
    }
}