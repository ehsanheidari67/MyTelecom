package ir.ipack.ehsan.local.ipack.myplan

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.ipack.ehsan.local.ipack.R
import ir.ipack.ehsan.local.ipack.ViewModelFactory
import ir.ipack.ehsan.local.ipack.activities.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_plan.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

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
        mViewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(activity!!.application)).get(MyPlanViewModel::class.java)

        subscribeToModels()
    }

    private fun subscribeToModels() {
        mViewModel.getPlanStream()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mPlanAdapter.setBasePlanInfo(it)
            }

        mViewModel.getDataCycleStream()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mPlanAdapter.setDataCycle(it)
            }
        mViewModel.getTalkCycleStream()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mPlanAdapter.setTalkCycle(it)
            }
        mViewModel.getTextCycleStream()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mPlanAdapter.setTextCycle(it)
            }
    }
}