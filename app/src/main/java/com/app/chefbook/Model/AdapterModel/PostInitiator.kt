package com.app.chefbook.Model.AdapterModel

import android.net.Uri

class PostInitiator(
    val postUri: Uri,
    val isPost: Boolean, // if true -> photo, else -> video
    val isAddImage: Boolean // if true -> add icon
)