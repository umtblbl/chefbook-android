package com.app.chefbook.ui.discoveryFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.app.chefbook.R
import com.app.chefbook.data.remote.manager.userManager.IUserManager
import com.app.chefbook.di.componentFragment
import com.app.chefbook.utility.getInflateLayout
import javax.inject.Inject

class DiscoveryFragment : Fragment() {

    @Inject
    lateinit var userManager: IUserManager
    private lateinit var viewModel: DiscoveryViewModel
    var toolbar: Toolbar? = null
    private var toolbarDiscovery: View? = null
    lateinit var searchView:SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_discovery, container, false)
        componentFragment.inject(this)
        viewModel = ViewModelProviders.of(this, DiscoveryViewModelFactory(userManager)).get(DiscoveryViewModel::class.java)
        initToolbar()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("SEARCH", "$query")
                searchView.clearFocus()
                searchView.setQuery("", false)
                //call
                viewModel.getSearchResult(query!!)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("SEARCH", "$newText")
                if (newText != null) {
                    Toast.makeText(context, "$newText", Toast.LENGTH_SHORT).show()
                    viewModel.getSearchResult(newText)
                }
                return true
            }
        })
    }

    private fun initToolbar() {
        toolbar = requireActivity().findViewById(R.id.toolbar_discovery_content)
        toolbar?.menu?.clear()
        toolbar?.title = ""
        toolbarDiscovery = context?.let { getInflateLayout(it, R.layout.toolbar_discovery) }

        searchView = toolbarDiscovery?.findViewById(R.id.searchViewDiscovery)!!

        toolbar?.addView(toolbarDiscovery)
    }

    override fun onDestroy() {
        super.onDestroy()
        toolbar?.removeView(toolbarDiscovery)
    }
}
