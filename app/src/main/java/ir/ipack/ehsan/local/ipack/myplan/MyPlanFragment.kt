package ir.ipack.ehsan.local.ipack.myplan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.ViewModelFactory
import ir.ipack.ehsan.local.ipack.activities.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_plan.view.*

class MyPlanFragment : Fragment() {

    private lateinit var planAdapter: MyPlanRecyclerAdapter
    private lateinit var viewModel: MyPlanViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_my_plan, container, false)
        val coorLayout = (activity as MainActivity).coordinator_layout
        planAdapter = MyPlanRecyclerAdapter(context!!, coorLayout)
        rootView.recyclerView.adapter = planAdapter
        rootView.recyclerView.layoutManager = LinearLayoutManager(activity)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(activity!!.application))
            .get(MyPlanViewModel::class.java)

        subscribeToModels()
    }

    private fun subscribeToModels() {
        viewModel.basePlan.observe(::getLifecycle) {
            planAdapter.setBasePlanInfo(it)
        }

        viewModel.dataCycle.observe(::getLifecycle) {
            planAdapter.setDataCycle(it)
        }
        viewModel.textCycle.observe(::getLifecycle) {
            planAdapter.setTextCycle(it)
        }
        viewModel.talkCycle.observe(::getLifecycle) {
            planAdapter.setTalkCycle(it)
        }
    }
}