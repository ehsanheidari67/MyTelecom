package ir.ipack.ehsan.local.ipack.mytalk

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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_talk.view.*

class MyTalkFragment : Fragment() {
    private lateinit var mTalkAdapter: MyTalkRecyclerAdapter
    private lateinit var mViewModel: MyTalkViewModel
    private lateinit var mResources: Resources
    private lateinit var mRootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_talk, container, false).also {
            mRootView = it
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(activity!!.application)).get(
            MyTalkViewModel::class.java
        )

        setupListAdapter()
        subscribeToModels()
    }

    private fun setupListAdapter() {
        val coorLayout = (activity as MainActivity).coordinator_layout
        mResources = activity!!.resources
        mRootView.my_talk_recyclerview.layoutManager = LinearLayoutManager(activity)
        mTalkAdapter = MyTalkRecyclerAdapter(context!!, coorLayout, mViewModel)
        mRootView.my_talk_recyclerview.adapter = mTalkAdapter
    }

    private fun subscribeToModels() {
        mViewModel.getTalkCycleStreamLive().observe(::getLifecycle) { cycleEntity ->
            cycleEntity?.let {
                mTalkAdapter.setCycle(it)
            }
        }

        mViewModel.getTalkUsageStreamLive().observe(::getLifecycle) { usageEntitiy ->
            usageEntitiy?.let {
                mTalkAdapter.setTalkUsage(it)
            }
        }
    }
}