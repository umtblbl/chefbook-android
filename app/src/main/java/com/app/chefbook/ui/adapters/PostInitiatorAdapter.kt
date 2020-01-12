package com.app.chefbook.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.model.AdapterModel.PostInitiator
import com.app.chefbook.R
import com.app.chefbook.utility.PostList
import kotlinx.android.synthetic.main.item_post_initiator.view.*

class PostInitiatorAdapter(
    private var postList: MutableList<PostInitiator>?,
    var onClickListener: RecyclerViewOnClickListener
) : RecyclerView.Adapter<PostInitiatorAdapter.PostInitiatorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostInitiatorViewHolder {
        val itemPost = LayoutInflater.from(parent.context).inflate(R.layout.item_post_initiator, parent, false)
        val holder = PostInitiatorViewHolder(itemPost)
        itemPost.imgDeletePost.setOnClickListener {
            PostList.instance!!.removeAt(holder.adapterPosition)
            notifyDataSetChanged()
        }

        return holder
    }

    override fun getItemCount(): Int {
        return postList?.size!!
    }

    override fun onBindViewHolder(holder: PostInitiatorViewHolder, position: Int) {

        val post = postList?.get(position)

        holder.setData(post!!, position, onClickListener)

    }

    class PostInitiatorViewHolder(var itemPost: View?) : RecyclerView.ViewHolder(itemPost!!) {

        var item = itemView as FrameLayout

        private var imgPost = item.imgPost
        private var videoPost = item.videoPost
        private var deletePost = item.imgDeletePost

        fun setData(
            post: PostInitiator,
            position: Int,
            onClickListener: RecyclerViewOnClickListener
        ) {

            if (post.isImage) {
                if (!post.isAddPost) {
                    imgPost.setPadding(0, 0, 0, 0)
                    deletePost.visibility = View.VISIBLE

                }
                else {
                    imgPost.setPadding(80, 80, 80, 80)
                    deletePost.visibility = View.GONE
                }

                videoPost.visibility = View.GONE
                imgPost.setImageURI(post.postUri)
                imgPost.visibility = View.VISIBLE
            } else {
                imgPost.visibility = View.GONE
                videoPost.setVideoURI(post.postUri)
                videoPost.visibility = View.VISIBLE
            }
            itemPost?.setOnClickListener { onClickListener.onClick(position.toString()) }
        }
    }
}