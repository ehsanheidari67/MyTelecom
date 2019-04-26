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
    private lateinit var textAdapter: MyTextRecyclerAdapter
    private lateinit var viewModel: MyTextViewModel
    private lateinit var appResources: Resources
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_text, container, false).also {
            rootView = it
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
        rootView.my_text_recyclerview.adapter = textAdapter
        rootView.my_text_recyclerview.layoutManager = LinearLayoutManager(activity)
    }

    private fun subscribeToModels() {
        viewModel.cycle.observe(::getLifecycle) {
            textAdapter.setCycle(it)
        }
        viewModel.usage.observe(::getLifecycle) {
            textAdapter.setTextUsage(it)
        }
    }
}