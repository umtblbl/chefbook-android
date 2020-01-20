package com.app.chefbook.ui.adapters.postInitiatorMedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.model.adapterModel.PostInitiatorMedia
import com.app.chefbook.R
import com.app.chefbook.ui.adapters.RecyclerViewOnClickListener
import com.app.chefbook.model.appModel.PostList
import kotlinx.android.synthetic.main.item_post_initiator_media.view.*

class PostInitiatorMediaAdapter(
    private var postListMedia: MutableList<PostInitiatorMedia>?,var onClickListener: RecyclerViewOnClickListener<Int>) : RecyclerView.Adapter<PostInitiatorMediaAdapter.PostInitiatorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostInitiatorViewHolder {
        val itemPost = LayoutInflater.from(parent.context).inflate(R.layout.item_post_initiator_media, parent, false)
        val holder =
            PostInitiatorViewHolder(
                itemPost
            )
        itemPost.imgDeletePost.setOnClickListener {
            PostList.instance!!.removeAt(holder.adapterPosition)
            notifyDataSetChanged()
        }

        return holder
    }

    override fun getItemCount(): Int {
        return postListMedia?.size!!
    }

    override fun onBindViewHolder(holder: PostInitiatorViewHolder, position: Int) {

        val post = postListMedia?.get(position)

        holder.setData(post!!, position, onClickListener)

    }

    class PostInitiatorViewHolder(var itemPost: View?) : RecyclerView.ViewHolder(itemPost!!) {

        var item = itemView as ConstraintLayout

        private var imgPost = item.imgPost
        private var deletePost = item.imgDeletePost

        fun setData(
            postMedia: PostInitiatorMedia,
            position: Int,
            onClickListener: RecyclerViewOnClickListener<Int>
        ) {

            if (postMedia.isImage) {
                if (!postMedia.isAddPost) {
                    imgPost.setPadding(0, 0, 0, 0)
                    deletePost.visibility = View.VISIBLE

                }
                else {
                    imgPost.setPadding(120, 120, 120, 120)
                    deletePost.visibility = View.GONE
                }

                imgPost.setImageURI(postMedia.postUri)
                imgPost.visibility = View.VISIBLE
            } else {
                imgPost.visibility = View.GONE
            }
            itemPost?.setOnClickListener { onClickListener.onClick(position.toString(), 0, 0) }
        }
    }
}