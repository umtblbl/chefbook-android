package com.app.chefbook.model.serviceModel.responseModel

data class UserFlowPost(
    val description: String,
    val likeCount: Int,
    val nameSurName: String,
    val postDate: String,
    val postId: String,
    val postImage: List<PostImage>,
    val profileImage: String,
    val star: Double,
    val title: String,
    val userName: String,
    val commentCount: Int
) {
    data class PostImage(
        val pictureUrl: String
    )
}