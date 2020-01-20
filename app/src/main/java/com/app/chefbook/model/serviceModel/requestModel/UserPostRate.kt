package com.app.chefbook.model.serviceModel.requestModel


import com.google.gson.annotations.SerializedName

data class UserPostRate(
    val postId: String,
    val rateNumber: Int
)