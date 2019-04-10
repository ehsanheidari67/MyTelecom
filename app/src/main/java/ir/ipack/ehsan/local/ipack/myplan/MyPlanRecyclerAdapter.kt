package ir.ipack.ehsan.local.ipack.myplan

import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.yqritc.recyclerviewmultipleviewtypesadapter.ListBindAdapter
import ir.ipack.ehsan.local.ipack.data.db.entity.BasePlanEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.myplan.binders.BasePlanBinder
import ir.ipack.ehsan.local.ipack.myplan.binders.PlanOverviewBinder

class MyPlanRecyclerAdapter(context: Context, coordinatorLayout: CoordinatorLayout) : ListBindAdapter() {

    init {
        val basePlanBinder = BasePlanBinder(this)
        val planOverviewBinder = PlanOverviewBinder(context, this)
        addAllBinder(basePlanBinder, planOverviewBinder)
    }

    fun setBasePlanInfo(basePlan: BasePlanEntity) {
        getDataBinder<BasePlanBinder>(0).add(basePlan)
    }

    fun setDataCycle(dataCycle: CycleEntity) {
        getDataBinder<PlanOverviewBinder>(1).addData(dataCycle)
    }

    fun setTalkCycle(talkCycle: CycleEntity) {
        getDataBinder<PlanOverviewBinder>(1).addTalk(talkCycle)
    }

    fun setTextCycle(textCycle: CycleEntity) {
        getDataBinder<PlanOverviewBinder>(1).addText(textCycle)
    }
}