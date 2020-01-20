package com.app.chefbook.ui.adapters.discoveryUser

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.model.serviceModel.responseModel.SearchResult

class DiscoveryViewHolder constructor(private val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bind(searchResult: SearchResult) {
        viewDataBinding.setVariable(BR.result, searchResult)
        viewDataBinding.executePendingBindings()
    }
}