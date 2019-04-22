package ir.ipack.ehsan.local.ipack.mytext

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
import kotlinx.android.synthetic.main.fragment_text.view.*

class MyTextFragment : Fragment() {
    private lateinit var mTextAdapter: MyTextRecyclerAdapter
    private lateinit var mViewModel: MyTextViewModel
    private lateinit var mResources: Resources
    private lateinit var mRootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_text, container, false).also {
            mRootView = it
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(activity!!.application)).get(
            MyTextViewModel::class.java
        )

        setupListAdapter()
        subscribeToModels()
    }

    private fun setupListAdapter() {
        val coorLayout = (activity as MainActivity).coordinator_layout
        mResources = activity!!.resources
        mTextAdapter = MyTextRecyclerAdapter(context!!, coorLayout, mViewModel)
        mRootView.my_text_recyclerview.adapter = mTextAdapter
        mRootView.my_text_recyclerview.layoutManager = LinearLayoutManager(activity)
    }

    private fun subscribeToModels() {
        mViewModel.getTextCycleStreamLive().observe(::getLifecycle) { cycleEntity ->
            cycleEntity?.let {
                mTextAdapter.setCycle(it)
            }
        }

        mViewModel.getTextUsageStreamLive().observe(::getLifecycle) { usageEntity ->
            usageEntity?.let {
                mTextAdapter.setTextUsage(it)
            }
        }

    }
}