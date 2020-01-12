package com.app.chefbook.utility

import com.app.chefbook.model.AdapterModel.PostInitiator

object PostList {
    private var postList: MutableList<PostInitiator>? = null

    val instance: MutableList<PostInitiator>?
        get() {
            if (postList == null) {
                postList = mutableListOf()
            }
            return postList
        }
}