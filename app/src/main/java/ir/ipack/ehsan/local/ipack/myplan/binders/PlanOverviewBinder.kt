package ir.ipack.ehsan.local.ipack.myplan.binders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBindAdapter
import com.yqritc.recyclerviewmultipleviewtypesadapter.DataBinder
import ir.ipack.ehsan.local.ipack.BR
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.data.db.entity.CycleEntity

class PlanOverviewBinder(dataBinderAdapter: DataBindAdapter) :
    DataBinder<PlanOverviewBinder.PlanOverviewHolder>(dataBinderAdapter) {
    private var dataCycle: CycleEntity? = null
    private var talkCycle: CycleEntity? = null
    private var textCycle: CycleEntity? = null


    override fun bindViewHolder(holder: PlanOverviewHolder?, position: Int) {
            holder?.bind(dataCycle, talkCycle, textCycle)
    }

    override fun getItemCount(): Int = 1

    override fun newViewHolder(parent: ViewGroup?): PlanOverviewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val viewDataBinding:ViewDataBinding
                = DataBindingUtil.inflate(inflater, R.layout.my_plan_header, parent, false)
        return PlanOverviewHolder(viewDataBinding)
    }

    class PlanOverviewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataCycle: CycleEntity?, talkCycle: CycleEntity?, textCycle: CycleEntity?){
            binding.setVariable(BR.dataCycle, dataCycle)
            binding.setVariable(BR.talkCycle, talkCycle)
            binding.setVariable(BR.textCycle, textCycle)
        }
    }

    fun addData(dataCycle: CycleEntity) {
        this.dataCycle = dataCycle
        notifyBinderDataSetChanged()
    }

    fun addTalk(talkCycle: CycleEntity) {
        this.talkCycle = talkCycle
        notifyBinderDataSetChanged()
    }

    fun addText(textCycle: CycleEntity) {
        this.textCycle = textCycle
        notifyBinderDataSetChanged()
    }
}