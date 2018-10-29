package ir.ipack.ehsan.local.ipack.myplan

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import com.yqritc.recyclerviewmultipleviewtypesadapter.ListBindAdapter
import ir.ipack.ehsan.local.ipack.data.BasePlan

class MyPlanRecyclerAdapter(context: Context?, coordinatorLayout: CoordinatorLayout) : ListBindAdapter() {

    init {
        val basePlanBinder = BasePlanBinder(this)
        addAllBinder(basePlanBinder)
    }

    fun setBasePlanInfo(basePlan : BasePlan){
        getDataBinder<BasePlanBinder>(0).add(basePlan)
    }
}