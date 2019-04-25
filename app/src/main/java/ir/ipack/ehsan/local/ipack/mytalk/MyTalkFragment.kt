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
    private lateinit var talkAdapter: MyTalkRecyclerAdapter
    private lateinit var viewModel: MyTalkViewModel
    private lateinit var appResources: Resources
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_talk, container, false).also {
            rootView = it
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(activity!!.application)).get(
            MyTalkViewModel::class.java
        )

        setupListAdapter()
        subscribeToModels()
    }

    private fun setupListAdapter() {
        val coorLayout = (activity as MainActivity).coordinator_layout
        appResources = activity!!.resources
        rootView.my_talk_recyclerview.layoutManager = LinearLayoutManager(activity)
        talkAdapter = MyTalkRecyclerAdapter(context!!, coorLayout, viewModel)
        rootView.my_talk_recyclerview.adapter = talkAdapter
    }

    private fun subscribeToModels() {
        viewModel.cycle.observe(::getLifecycle) {
            talkAdapter.setCycle(it)
        }
        viewModel.usage.observe(::getLifecycle) {
            talkAdapter.setTalkUsage(it)
        }
    }
}