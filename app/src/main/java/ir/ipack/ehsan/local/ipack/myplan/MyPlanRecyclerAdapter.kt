package ir.ipack.ehsan.local.ipack.myplan

import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.yqritc.recyclerviewmultipleviewtypesadapter.ListBindAdapter
import ir.ipack.ehsan.local.ipack.data.BasePlan
import ir.ipack.ehsan.local.ipack.data.Cycle
import ir.ipack.ehsan.local.ipack.myplan.binders.BasePlanBinder
import ir.ipack.ehsan.local.ipack.myplan.binders.PlanOverviewBinder

class MyPlanRecyclerAdapter(context: Context, coordinatorLayout: CoordinatorLayout) : ListBindAdapter() {

    init {
        val basePlanBinder = BasePlanBinder(this)
        val planOverviewBinder = PlanOverviewBinder(context, this)
        addAllBinder(basePlanBinder, planOverviewBinder)
    }

    fun setBasePlanInfo(basePlan: BasePlan) {
        getDataBinder<BasePlanBinder>(0).add(basePlan)
    }

    fun setDataCycle(dataCycle: Cycle) {
        getDataBinder<PlanOverviewBinder>(1).addData(dataCycle)
    }

    fun setTalkCycle(talkCycle: Cycle) {
        getDataBinder<PlanOverviewBinder>(1).addTalk(talkCycle)
    }

    fun setTextCycle(textCycle: Cycle) {
        getDataBinder<PlanOverviewBinder>(1).addText(textCycle)
    }
}