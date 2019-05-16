package ir.ipack.ehsan.local.ipack.mydata

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
import ir.ipack.ehsan.local.ipack.databinding.FragmentMyDataBinding
import ir.ipack.ehsan.local.ipack.utils.RecyclerDivider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_data.*
import kotlinx.android.synthetic.main.fragment_my_data.view.*
import timber.log.Timber

class MyDataFragment : Fragment() {
    private lateinit var dataAdapter: MyDataRecyclerAdapter
    private lateinit var viewModel: MyDataViewModel
    private lateinit var appResources: Resources
    private lateinit var rootView: View
    private lateinit var binding: FragmentMyDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_data, container,
            false)
        return binding.root.also {
            rootView = it
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(activity!!.application)).get(
            MyDataViewModel::class.java
        )

        setupListAdapter()
        subscribeToModels()
        initialDataRecyclerList()
    }

    private fun setupListAdapter() {
        val coorLayout = (activity as MainActivity).coordinator_layout
        appResources = activity!!.resources
        dataAdapter = MyDataRecyclerAdapter(context!!, coorLayout, viewModel)

        binding.adapter = dataAdapter
        rootView.my_data_recyclerview.layoutManager = LinearLayoutManager(activity)
    }

    private fun subscribeToModels() {

        viewModel.usages.observe(::getLifecycle) {
            dataAdapter.setAppUsage(it)
            showContentAndHideProgressBar()
        }

        viewModel.cycle.observe(::getLifecycle) {
            dataAdapter.setCycle(it)
            showContentAndHideProgressBar()
        }

        viewModel.failure.observe(::getLifecycle) {
            Timber.e("Failure")
        }
    }

    private fun initialDataRecyclerList() {
        dataAdapter.setDividerHeader(RecyclerDivider(appResources.getString(R.string.app_usage_header), -1))
    }

    private fun showContentAndHideProgressBar() {
        showContent()
        hideProgressBar()
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun showContent() {
        my_data_recyclerview.visibility = View.VISIBLE
    }
}