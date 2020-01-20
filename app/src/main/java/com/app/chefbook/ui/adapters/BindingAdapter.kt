package com.app.chefbook.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.model.serviceModel.responseModel.Profile
import com.app.chefbook.model.serviceModel.responseModel.SearchResult
import com.app.chefbook.model.serviceModel.responseModel.UserFlowPost
import com.app.chefbook.ui.adapters.discoveryUser.DiscoveryAdapter
import com.app.chefbook.ui.adapters.profilePost.ProfilePostAdapter
import com.app.chefbook.ui.adapters.slidingMedia.SlidingMediaAdapter
import com.app.chefbook.ui.adapters.userFlowPost.UserFlowPostAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("android:profile")
fun bindAdapter(recyclerView: RecyclerView, profile: Profile?) {
    val adapter = recyclerView.adapter
    profile?.let { if (adapter is ProfilePostAdapter) adapter.setProfile(it) }
}

@BindingAdapter("android:flowPost")
fun bindAdapter(recyclerView: RecyclerView, flowPostList: List<UserFlowPost>?) {
    val adapter = recyclerView.adapter
    flowPostList?.let { if (adapter is UserFlowPostAdapter) adapter.setFlowPost(it) }
}


@JvmName("searchResult")
@BindingAdapter("android:searchResult")
fun bindAdapter(recyclerView: RecyclerView, searchResultList: List<SearchResult>?) {
    val adapter = recyclerView.adapter
    searchResultList?.let { if (adapter is DiscoveryAdapter) adapter.setSearchResult(it) }
}


