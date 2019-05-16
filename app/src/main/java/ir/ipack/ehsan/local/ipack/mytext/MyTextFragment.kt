package ir.ipack.ehsan.local.ipack.mytext

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
import ir.ipack.ehsan.local.ipack.databinding.FragmentTextBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_data.progressBar
import kotlinx.android.synthetic.main.fragment_text.*
import kotlinx.android.synthetic.main.fragment_text.view.*

class MyTextFragment : Fragment() {
    private lateinit var textAdapter: MyTextRecyclerAdapter
    private lateinit var viewModel: MyTextViewModel
    private lateinit var appResources: Resources
    private lateinit var rootView: View
    private lateinit var binding: FragmentTextBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_text, container, false
        )
        return binding.root.also {
            rootView = it
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(activity!!.application)).get(
            MyTextViewModel::class.java
        )

        setupListAdapter()
        subscribeToModels()
    }

    private fun setupListAdapter() {
        val coorLayout = (activity as MainActivity).coordinator_layout
        appResources = activity!!.resources
        textAdapter = MyTextRecyclerAdapter(context!!, coorLayout, viewModel)
        binding.adapter = textAdapter
        rootView.my_text_recyclerview.layoutManager = LinearLayoutManager(activity)
    }

    private fun subscribeToModels() {
        viewModel.cycle.observe(::getLifecycle) {
            textAdapter.setCycle(it)
            showContentAndHideProgressBar()
        }
        viewModel.usage.observe(::getLifecycle) {
            textAdapter.setTextUsage(it)
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
        my_text_recyclerview.visibility = View.VISIBLE
    }
}