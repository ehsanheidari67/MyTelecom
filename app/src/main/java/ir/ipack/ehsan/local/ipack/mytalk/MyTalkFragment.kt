package ir.ipack.ehsan.local.ipack.mytalk

import android.content.res.Resources
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
import ir.ipack.ehsan.local.ipack.databinding.FragmentTalkBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_data.progressBar
import kotlinx.android.synthetic.main.fragment_talk.*
import kotlinx.android.synthetic.main.fragment_talk.view.*

class MyTalkFragment : Fragment() {
    private lateinit var talkAdapter: MyTalkRecyclerAdapter
    private lateinit var viewModel: MyTalkViewModel
    private lateinit var appResources: Resources
    private lateinit var rootView: View
    private lateinit var binding: FragmentTalkBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_talk, container, false
        )
        return binding.root.also {
            rootView = it
        }
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
        binding.adapter = talkAdapter
    }

    private fun subscribeToModels() {
        viewModel.cycle.observe(::getLifecycle) {
            talkAdapter.setCycle(it)
            showContentAndHideProgressBar()
        }
        viewModel.usage.observe(::getLifecycle) {
            talkAdapter.setTalkUsage(it)
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
        my_talk_recyclerview.visibility = View.VISIBLE
    }

}