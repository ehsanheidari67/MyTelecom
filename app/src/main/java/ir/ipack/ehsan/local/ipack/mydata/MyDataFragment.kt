package ir.ipack.ehsan.local.ipack.mydata

import android.content.res.Resources
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
import ir.ipack.ehsan.local.ipack.utils.RecyclerDivider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_data.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MyDataFragment : Fragment() {
    private lateinit var mDataAdapter: MyDataRecyclerAdapter
    private lateinit var mViewModel: MyDataViewModel
    private lateinit var mResources: Resources
    private lateinit var mRootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_my_data, container, false).also {
            mRootView = it
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(activity!!.application)).get(
            MyDataViewModel::class.java
        )

        setupListAdapter()
        subscribeToModels()
        initialDataRecyclerList()
    }

    private fun setupListAdapter() {
        val coorLayout = (activity as MainActivity).coordinator_layout
        mResources = activity!!.resources
        mDataAdapter = MyDataRecyclerAdapter(context!!, coorLayout, mViewModel)
        mRootView.my_data_recyclerview.adapter = mDataAdapter
        mRootView.my_data_recyclerview.layoutManager = LinearLayoutManager(activity)
    }

    private fun subscribeToModels() {
        mViewModel.getDataCycleStream()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mDataAdapter.setCycle(it)
            }

        mViewModel.getUsagesStreamLive().observe(::getLifecycle) {
            mDataAdapter.setAppUsage(it)
        }
    }

    private fun initialDataRecyclerList() {
        mDataAdapter.setDividerHeader(RecyclerDivider(mResources.getString(R.string.app_usage_header), -1))
    }
}