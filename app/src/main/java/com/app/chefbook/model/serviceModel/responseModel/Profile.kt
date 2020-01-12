package com.app.chefbook.model.serviceModel.responseModel

data class Profile(
    val cover: String,
    val description: String,
    val followerCount: Int,
    val fullName: String,
    val postCount: Int,
    val profilePicture: String,
    val profilePosts: List<ProfilePost>,
    val userName: String
)

{
    data class ProfilePost(
        val commentCount: String,
        val description: String,
        val id: String,
        val pictureUrl: String,
        val rateNumber: String,
        val title: String,
        val likeCount: String
    )
}