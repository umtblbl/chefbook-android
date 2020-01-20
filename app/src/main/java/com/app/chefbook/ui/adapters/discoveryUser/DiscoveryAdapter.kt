package com.app.chefbook.ui.adapters.discoveryUser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.library.baseAdapters.BR
import com.app.chefbook.R
import com.app.chefbook.model.serviceModel.responseModel.SearchResult

class DiscoveryAdapter() :RecyclerView.Adapter<DiscoveryViewHolder>() {

    private lateinit var searchResultList: List<SearchResult>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoveryViewHolder {
        val holder = DiscoveryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_discovery,
                parent,
                false

            )
        )

        //snapHelper = PagerSnapHelper()
        //snapHelper.attachToRecyclerView(holder.itemView.recViewSlidingMedia)

        return holder
    }

    override fun getItemCount(): Int {
        return searchResultList.size
    }

    override fun onBindViewHolder(holder: DiscoveryViewHolder, position: Int) {
        val flowPost = searchResultList[position]
        holder.bind(flowPost)
    }


    fun setSearchResult(searchResultList: List<SearchResult>) {
        this.searchResultList = searchResultList
        notifyDataSetChanged()
    }
}