package com.app.chefbook.UI.Adapters

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.Model.AppModel.GalleryPicture
import com.app.chefbook.R

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.multi_gallery_listitem.*
import kotlinx.android.synthetic.main.multi_gallery_listitem.view.*

class GalleryPicturesAdapter(private val list: List<GalleryPicture>) : RecyclerView.Adapter<GVH>() {

    init {
        initSelectedIndexList()
    }

    constructor(list: List<GalleryPicture>, selectionLimit: Int) : this(list) {
        setSelectionLimit(selectionLimit)
    }

    private lateinit var onClick: (GalleryPicture) -> Unit
    private lateinit var afterSelectionCompleted: () -> Unit
    private var isSelectionEnabled = false
    private lateinit var selectedIndexList: ArrayList<Int> // only limited items are selectable.
    private var selectionLimit = 0


    private fun initSelectedIndexList() {
        selectedIndexList = ArrayList(selectionLimit)
    }

    private fun setSelectionLimit(selectionLimit: Int) {
        this.selectionLimit = selectionLimit
        removedSelection()
        initSelectedIndexList()
    }

    fun setOnClickListener(onClick: (GalleryPicture) -> Unit) {
        this.onClick = onClick
    }

    fun setAfterSelectionListener(afterSelectionCompleted: () -> Unit) {
        this.afterSelectionCompleted = afterSelectionCompleted
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GVH {
        val vh = GVH(LayoutInflater.from(p0.context).inflate(R.layout.multi_gallery_listitem, p0, false))

        vh.containerView.setOnClickListener {
            val position = vh.adapterPosition
            val picture = getItem(position)

            if (isSelectionEnabled) {
                handleSelection(position, it.context)
                notifyItemChanged(position)
                checkSelection(position)
                afterSelectionCompleted()

            } else {
                onClick(picture)
            }

        }

        vh.containerView.setOnLongClickListener {
            val position = vh.adapterPosition
            isSelectionEnabled = true
            handleSelection(position, it.context)
            notifyItemChanged(position)
            checkSelection(position)
            afterSelectionCompleted()

            isSelectionEnabled
        }
        return vh
    }

    private fun checkSelection(position: Int) {
        if (isSelectionEnabled) {
            if (getItem(position).isSelected)
                selectedIndexList.add(position)
            else {
                selectedIndexList.remove(position)
                isSelectionEnabled = selectedIndexList.isNotEmpty()
            }
            Log.e("Selected", "---------${position}-------------")

        }
    }
    private fun handleSelection(position: Int, context: Context) {

        val picture = getItem(position)

        picture.isSelected = if (picture.isSelected) { false }
        else {
            val selectionCriteriaSuccess = getSelectedItems().size < selectionLimit
            if (!selectionCriteriaSuccess)
                selectionLimitReached(context)

            selectionCriteriaSuccess
        }

    }

    fun getSelectionLimit() = selectionLimit


    private fun selectionLimitReached(context: Context) {
        Toast.makeText(
            context,
            "Bir gönderiye en fazla 6 adet medya yükleyebilirsin.",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun getItem(position: Int) = list[position]

    override fun onBindViewHolder(holder: GVH, pos: Int) {
        val picture = list[pos]
        Glide.with(holder.itemView.context).load(picture.path).into(holder.itemView.ivImg)

        if (!picture.isSelected) {
            holder.itemView.vSelected.setImageResource(R.drawable.ic_check_circle_white_24dp)
        } else {
            holder.itemView.vSelected.visibility = View.VISIBLE
            holder.itemView.vSelected.setImageResource(R.drawable.ic_check_circle_coloraccent_24dp)
        }
    }

    override fun getItemCount() = list.size

    fun getSelectedItems() = selectedIndexList.map { list[it] }

    private fun removedSelection(): Boolean {
        return if (isSelectionEnabled) {
            selectedIndexList.forEach {
                list[it].isSelected = false
            }
            isSelectionEnabled = false
            selectedIndexList.clear()
            notifyDataSetChanged()
            true

        } else false
    }
}