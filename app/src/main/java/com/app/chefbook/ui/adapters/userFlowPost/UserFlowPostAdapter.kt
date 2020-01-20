package com.app.chefbook.ui.adapters.userFlowPost

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.R
import com.app.chefbook.model.serviceModel.responseModel.UserFlowPost
import com.app.chefbook.ui.adapters.RecyclerViewOnClickListener
import com.app.chefbook.ui.adapters.slidingMedia.SlidingMediaAdapter
import com.app.chefbook.utility.RateDialog
import kotlinx.android.synthetic.main.item_flow_post.view.*
import kotlinx.coroutines.flow.flow

class UserFlowPostAdapter(var onClickListener: RecyclerViewOnClickListener<Int>, var manager: FragmentManager) :
    RecyclerView.Adapter<UserFlowViewHolder>() {

    private lateinit var flowPostList: List<UserFlowPost>
    private lateinit var slidingMediaAdapter: SlidingMediaAdapter
    lateinit var slidingRecyclerView: RecyclerView
    lateinit var snapHelper: PagerSnapHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFlowViewHolder {
        val holder = UserFlowViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_flow_post,
                parent,
                false

            )
        )

        //snapHelper = PagerSnapHelper()
        //snapHelper.attachToRecyclerView(holder.itemView.recViewSlidingMedia)

        return holder
    }

    override fun getItemCount(): Int {
        return flowPostList.size
    }

    override fun onBindViewHolder(holder: UserFlowViewHolder, position: Int) {
        val flowPost = flowPostList[position]
        holder.bind(flowPost)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(flowPost.postId,0, 1)
        }

        holder.itemView.imgLike.setOnClickListener {
            onClickListener.onClick(flowPost.postId, 0,2)
            holder.itemView.imgLike.setImageResource(R.drawable.ic_favorite_white_24dp)
            val likeCount =(holder.itemView.txtLikeCount.text.toString())
            val newLikeCount = likeCount.toInt() + 1
            holder.itemView.txtLikeCount.text = newLikeCount.toString()
        }

        holder.itemView.lnStar.setOnClickListener {

            val rateDialog = RateDialog(flowPost.postId)
            rateDialog.show(manager, "RateDialog")

        }

        slidingRecyclerView = holder.itemView.recViewSlidingMedia
        initFlowPostRecyclerView(slidingRecyclerView, flowPost.postImage)
    }


    fun setFlowPost(flowPostList: List<UserFlowPost>) {
        this.flowPostList = flowPostList
        notifyDataSetChanged()
    }

    private fun initFlowPostRecyclerView(recViewSlidingMedia: RecyclerView, pictureList: List<UserFlowPost.PostImage>) {

        slidingMediaAdapter = SlidingMediaAdapter(pictureList)
        recViewSlidingMedia.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = slidingMediaAdapter
            setHasFixedSize(true)
        }


        slidingMediaAdapter.notifyDataSetChanged()

    }
}