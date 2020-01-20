package com.app.chefbook.ui.adapters

interface RecyclerViewOnClickListener<T> {
    fun onClick(id: String, value: T, type: Int){}
}