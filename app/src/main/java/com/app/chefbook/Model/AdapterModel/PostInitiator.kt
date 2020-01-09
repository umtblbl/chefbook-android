package com.app.chefbook.Model.AdapterModel

import android.net.Uri

class PostInitiator(
    val postUri: Uri,
    val isImage: Boolean, // if true -> photo, else -> video
    val isAddPost: Boolean // if true -> add icon
)