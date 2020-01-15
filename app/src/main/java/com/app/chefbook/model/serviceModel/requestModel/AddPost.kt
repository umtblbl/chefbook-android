package com.app.chefbook.model.serviceModel.requestModel

import okhttp3.MultipartBody

data class AddPost(
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val photos: List<MultipartBody.Part>)