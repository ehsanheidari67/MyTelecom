package ir.ipack.ehsan.local.ipack.myplan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.ViewModelFactory
import ir.ipack.ehsan.local.ipack.activities.MainActivity
import ir.ipack.ehsan.local.ipack.databinding.FragmentMyPlanBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_data.progressBar
import kotlinx.android.synthetic.main.fragment_my_plan.*
import kotlinx.android.synthetic.main.fragment_my_plan.view.*

class MyPlanFragment : Fragment() {

    private lateinit var planAdapter: MyPlanRecyclerAdapter
    private lateinit var viewModel: MyPlanViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMyPlanBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_my_plan,
            container, false
        )

        val coorLayout = (activity as MainActivity).coordinator_layout
        planAdapter = MyPlanRecyclerAdapter(context!!, coorLayout)
        binding.root.recyclerView.layoutManager = LinearLayoutManager(activity)

        binding.adapter = planAdapter

        return binding.root
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
            showContentAndHideProgressBar()
        }

        viewModel.dataCycle.observe(::getLifecycle) {
            planAdapter.setDataCycle(it)
            showContentAndHideProgressBar()
        }
        viewModel.textCycle.observe(::getLifecycle) {
            planAdapter.setTextCycle(it)
            showContentAndHideProgressBar()
        }
        viewModel.talkCycle.observe(::getLifecycle) {
            planAdapter.setTalkCycle(it)
            showContentAndHideProgressBar()
        }
    }

    private fun showContentAndHideProgressBar() {
        showContent()
        hideProgressBar()
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun showContent() {
        recyclerView.visibility = View.VISIBLE
    }
}