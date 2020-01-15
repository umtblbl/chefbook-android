package com.app.chefbook.utility

import com.app.chefbook.model.AdapterModel.PostInitiatorMedia

object PostList {
    private var postListMedia: MutableList<PostInitiatorMedia>? = null

    val instance: MutableList<PostInitiatorMedia>?
        get() {
            if (postListMedia == null) {
                postListMedia = mutableListOf()
            }
            return postListMedia
        }
}