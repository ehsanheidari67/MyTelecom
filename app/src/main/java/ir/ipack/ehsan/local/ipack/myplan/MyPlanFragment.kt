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

    private lateinit var mPlanAdapter: MyPlanRecyclerAdapter
    private lateinit var mViewModel: MyPlanViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_my_plan, container, false)
        val coorLayout = (activity as MainActivity).coordinator_layout
        mPlanAdapter = MyPlanRecyclerAdapter(context!!, coorLayout)
        rootView.recyclerView.adapter = mPlanAdapter
        rootView.recyclerView.layoutManager = LinearLayoutManager(activity)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(activity!!.application))
            .get(MyPlanViewModel::class.java)

        subscribeToModels()
    }

    private fun subscribeToModels() {
        mViewModel.basePlan.observe(::getLifecycle) {
            mPlanAdapter.setBasePlanInfo(it)
        }

        mViewModel.dataCycle.observe(::getLifecycle) {
            mPlanAdapter.setDataCycle(it)
        }
        mViewModel.textCycle.observe(::getLifecycle) {
            mPlanAdapter.setTextCycle(it)
        }
        mViewModel.talkCycle.observe(::getLifecycle) {
            mPlanAdapter.setTalkCycle(it)
        }
    }
}