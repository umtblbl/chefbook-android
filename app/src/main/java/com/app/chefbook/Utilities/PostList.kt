package com.app.chefbook.Utilities

import com.app.chefbook.Model.AdapterModel.PostInitiator
import java.util.*

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