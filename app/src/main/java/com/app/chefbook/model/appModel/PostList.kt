package com.app.chefbook.model.appModel

import com.app.chefbook.model.adapterModel.PostInitiatorMedia

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