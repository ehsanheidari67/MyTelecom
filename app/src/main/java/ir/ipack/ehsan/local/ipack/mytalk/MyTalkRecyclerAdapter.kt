package ir.ipack.ehsan.local.ipack.mytalk

import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.yqritc.recyclerviewmultipleviewtypesadapter.ListBindAdapter
import ir.ipack.ehsan.local.ipack.BaseViewModel
import ir.ipack.ehsan.local.ipack.commonbinders.CycleBinder
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity
import ir.ipack.ehsan.local.ipack.data.db.entity.UsageEntity
import ir.ipack.ehsan.local.ipack.mytalk.binders.TalkIncomingOutgoingBinder

class MyTalkRecyclerAdapter(context: Context, coordinatorLayout: CoordinatorLayout, baseViewModel: BaseViewModel) :
    ListBindAdapter() {
    init {
        val cycleBinder = CycleBinder(context, this, coordinatorLayout, baseViewModel)
        val talkUsageBinder = TalkIncomingOutgoingBinder(this)
        addAllBinder(cycleBinder, talkUsageBinder)
    }

    fun setCycle(cycle: CycleEntity) {
        getDataBinder<CycleBinder>(0).add(cycle)
    }

    fun setTalkUsage(talkUsage: UsageEntity) {
        getDataBinder<TalkIncomingOutgoingBinder>(1).add(talkUsage)
    }
}