package com.app.chefbook.ui.flowFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.chefbook.R
import com.app.chefbook.data.remote.manager.userManager.IUserManager
import com.app.chefbook.data.remote.service.postService.IPostService
import com.app.chefbook.data.remote.service.postService.PostService
import com.app.chefbook.databinding.FragmentFlowBinding
import com.app.chefbook.di.componentFragment
import com.app.chefbook.ui.adapters.RecyclerViewOnClickListener
import com.app.chefbook.ui.adapters.userFlowPost.UserFlowPostAdapter
import com.app.chefbook.utility.getInflateLayout
import javax.inject.Inject


class FlowFragment : Fragment(), RecyclerViewOnClickListener<Int> {

    @Inject
    lateinit var userManager: IUserManager

    @Inject
    lateinit var postService: IPostService

    private lateinit var viewModel: FlowViewModel
    private lateinit var flowBinding: FragmentFlowBinding
    private val flowPostAdapter: UserFlowPostAdapter by lazy { UserFlowPostAdapter(this, fragmentManager!!) }
    var toolbar: Toolbar? = null
    private var toolbarFlowFragment: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        componentFragment.inject(this)
        flowBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_flow, container, false)
        viewModel = ViewModelProviders.of(this, FlowViewModelFactory(userManager, postService)).get(FlowViewModel::class.java)
        initBindingObserver()
        initToolbar()

        viewModel.getUserFlowPost()
        viewModel.flowPostList.observe(this, Observer {
            initFlowPostRecyclerView()
        })

        return flowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initBindingObserver() {
        flowBinding.run {
            flowViewModel = viewModel
            lifecycleOwner = this@FlowFragment
        }
    }

    private fun initFlowPostRecyclerView() {
        flowBinding.recViewFlow.run {
            layoutManager = LinearLayoutManager(
                flowBinding.root.context,
                LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            adapter = flowPostAdapter
        }
    }

    override fun onClick(id: String, value: Int, type: Int) {
        when(type) {
            1 -> {
                //profile
                 }
            2 -> {
                viewModel.sendPostLike(id)
            }
            3 -> {
                Toast.makeText(context, "$value", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initToolbar() {
        toolbar = requireActivity().findViewById(R.id.toolbar_flow_content)
        toolbar?.menu?.clear()
        toolbar?.title = ""
        toolbarFlowFragment = context?.let { getInflateLayout(it, R.layout.toolbar_flow) }

        //imgToolbarSend = toolbarPostInitiator?.findViewById(R.id.imgSend)

        toolbar?.addView(toolbarFlowFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        toolbar?.removeView(toolbarFlowFragment)
    }

    override fun onPause() {
        super.onPause()
        Log.e("PAUSE", "FLOWFRAGMENT")
    }
}
