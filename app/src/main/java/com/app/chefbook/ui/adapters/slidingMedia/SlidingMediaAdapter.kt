package com.app.chefbook.ui.adapters.slidingMedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.R
import com.app.chefbook.model.serviceModel.responseModel.UserFlowPost
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_sliding_media.view.*

class SlidingMediaAdapter(private var pictureList : List<UserFlowPost.PostImage>): RecyclerView.Adapter<SlidingMediaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlidingMediaViewHolder {
        return SlidingMediaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sliding_media, parent, false))

    }
    override fun getItemCount(): Int { return pictureList.size  }

    override fun onBindViewHolder(holder: SlidingMediaViewHolder, position: Int) {
        val flowPost = pictureList[position]
        Glide.with(holder.itemView.context).load(flowPost.pictureUrl).into(holder.itemView.imgPost)

    }
}