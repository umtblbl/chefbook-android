package com.app.chefbook.ui.adapters

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.model.serviceModel.responseModel.Profile

class ProfilePostViewHolder constructor(private val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bind(profile: Profile.ProfilePost) {
        viewDataBinding.setVariable(BR.profilePost, profile)
        viewDataBinding.executePendingBindings()
    }
}