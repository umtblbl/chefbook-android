package com.app.chefbook.ui.adapters.userFlowPost

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.model.serviceModel.responseModel.UserFlowPost

class UserFlowViewHolder constructor(private val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bind(flowPost: UserFlowPost) {
        viewDataBinding.setVariable(BR.flowPost, flowPost)
        viewDataBinding.executePendingBindings()
    }
}