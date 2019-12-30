package com.app.chefbook.UI.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.Model.ServiceModel.ResponseModel.Profile
import com.app.chefbook.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_profile_post.view.*

class ProfilePostAdapter(
    private var profile: Profile,
    var onClickListener: RecyclerViewOnClickListener
) : RecyclerView.Adapter<ProfilePostAdapter.ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemPost = inflater.inflate(R.layout.item_profile_post, parent, false)
        return ProfileViewHolder(itemPost)

    }

    override fun getItemCount(): Int {
        return profile.profilePosts.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val post = profile.profilePosts[position]
        holder.setData(post, onClickListener)
    }


    class ProfileViewHolder(var itemPost: View?) : RecyclerView.ViewHolder(itemPost!!) {

        var item = itemView as RelativeLayout

        private var txtTitle = item.txtPostTitle
        private var imgPicture = item.imgPost
        private var txtDescription = item.txtPostDescription
        private var txtRate = item.txtPostRate
        private var txtLikeCount = item.txtLikeCount
        private var txtCommentCount = item.txtCommentCount

        fun setData(post: Profile.ProfilePost, onClickListener: RecyclerViewOnClickListener) {

            txtTitle.text = post.title
            txtDescription.text = post.description
            txtRate.text = post.rateNumber
            txtLikeCount.text = post.likeCount
            txtCommentCount.text = post.commentCount
            Picasso.with(item.context).load(post.pictureUrl).into(imgPicture)

            itemPost?.setOnClickListener { onClickListener.onClick(post.id) }
        }
    }
}