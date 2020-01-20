package com.app.chefbook.ui.adapters.profilePost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.model.serviceModel.responseModel.Profile
import com.app.chefbook.R
import com.app.chefbook.ui.adapters.RecyclerViewOnClickListener

class ProfilePostAdapter(var onClickListener: RecyclerViewOnClickListener<Int>) :RecyclerView.Adapter<ProfilePostViewHolder>() {

    private lateinit var profile: Profile

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostViewHolder =
        ProfilePostViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_profile_post,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return profile.profilePosts.size
    }

    override fun onBindViewHolder(holder: ProfilePostViewHolder, position: Int) {
        val profilePost = profile.profilePosts[position]
        holder.bind(profilePost)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(holder.adapterPosition.toString(), 0, 0)
        }
    }

    fun setProfile(profile: Profile) {
        this.profile = profile
        notifyDataSetChanged()
    }

}