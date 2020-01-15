package com.app.chefbook.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.model.serviceModel.responseModel.Profile
import com.app.chefbook.ui.adapters.profilePost.ProfilePostAdapter
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