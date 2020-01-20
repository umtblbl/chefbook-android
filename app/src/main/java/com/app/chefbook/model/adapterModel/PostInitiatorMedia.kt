package com.app.chefbook.model.adapterModel

import android.net.Uri

class PostInitiatorMedia(
    val postUri: Uri,
    val isImage: Boolean, // if true -> photo, else -> video
    val isAddPost: Boolean // if true -> add icon
)