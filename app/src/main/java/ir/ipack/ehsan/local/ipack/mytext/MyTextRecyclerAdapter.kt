package ir.ipack.ehsan.local.ipack.mytext

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import com.yqritc.recyclerviewmultipleviewtypesadapter.ListBindAdapter
import ir.ipack.ehsan.local.ipack.commonbinders.CycleBinder
import ir.ipack.ehsan.local.ipack.data.Cycle
import ir.ipack.ehsan.local.ipack.data.Usage
import ir.ipack.ehsan.local.ipack.mytext.binders.TextIncomingOutgoingBinder

class MyTextRecyclerAdapter(context: Context, coordinatorLayout: CoordinatorLayout) : ListBindAdapter() {
    init {
        val cycleBinder = CycleBinder(context, this, coordinatorLayout)
        val textUsageBinder = TextIncomingOutgoingBinder(context, this)
        addAllBinder(cycleBinder, textUsageBinder)
    }

    fun setCycle(cycle: Cycle) {
        getDataBinder<CycleBinder>(0).add(cycle)
    }


    fun setTextUsage(textUsage: Usage){
        getDataBinder<TextIncomingOutgoingBinder>(1).add(textUsage)
    }
}