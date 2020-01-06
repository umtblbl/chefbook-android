package com.app.chefbook.UI.Adapters

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.Model.AdapterModel.PostInitiator
import com.app.chefbook.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post_initiator.view.*

class PostInitiatorAdapter(private var postList: MutableList<PostInitiator>, var onClickListener: RecyclerViewOnClickListener) : RecyclerView.Adapter<PostInitiatorAdapter.PostInitiatorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostInitiatorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemPost = inflater.inflate(R.layout.item_post_initiator, parent, false)
        return PostInitiatorViewHolder(itemPost)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostInitiatorViewHolder, position: Int) {
        val post = postList[position]

        holder.setData(post, position, onClickListener)
        /* if ((position+1) == postList.size) {
             holder.setData(post, (position+1), true, onClickListener)
         }*/
    }

    class PostInitiatorViewHolder(var itemPost: View?) : RecyclerView.ViewHolder(itemPost!!) {

        var item = itemView as FrameLayout

        private var imgPost = item.imgPost
        private var videoPost = item.videoPost

        @SuppressLint("ResourceAsColor")
        fun setData(post: PostInitiator, position: Int, onClickListener: RecyclerViewOnClickListener) {

            if (post.isPost) {
                if(post.isAddImage)
                    imgPost.setPadding(80,80,80,80)
                else
                    imgPost.setPadding(0,0,0,0)

                videoPost.visibility = View.GONE
                imgPost.setImageURI(post.postUri)
                imgPost.visibility = View.VISIBLE
            } else {
                imgPost.visibility = View.GONE
                videoPost.setVideoURI(post.postUri)
                videoPost.visibility = View.VISIBLE
            }

            // postValue if true -> photo, else -> video
            // isPost if true -> Post, else -> value should be postAddImage
            /*if (isAddImage) {
                Log.d("recycler", "isAddImage:true")
                videoPost.visibility = View.GONE
                //imgPost.setPadding(30, 30, 30, 30)
                imgPost.setImageResource(R.drawable.ic_add_a_photo_white_24dp)
                imgPost.visibility = View.VISIBLE
            }*/

            itemPost?.setOnClickListener { onClickListener.onClick(position.toString()) }
        }
    }
}