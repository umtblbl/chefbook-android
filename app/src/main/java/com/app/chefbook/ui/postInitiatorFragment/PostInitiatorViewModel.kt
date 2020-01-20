package com.app.chefbook.ui.postInitiatorFragment

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.service.postService.PostService
import com.app.chefbook.model.appModel.PostList
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class PostInitiatorViewModel(val postService: PostService) : ViewModel() {

    var isPostState = MutableLiveData<String>()

    fun sendPost(title: String, description: String, steps: Array<String>, ingredients: Array<String>, photos: Array<MultipartBody.Part>) {
        postService.sendPost(title, description, steps, ingredients, photos, object : ServiceCallBack<String> {
            override fun onResponse(response: String) {
                isPostState.postValue(response)
            }

            override fun onError(message: String) {
                isPostState.postValue(message)
            }
        })
    }

    fun getPostMedia(): MutableList<MultipartBody.Part> {

        var postList = mutableListOf<MultipartBody.Part>()

        PostList.instance!!.forEachIndexed { _, post ->
            if (!post.isAddPost) {
                postList.add(prepareFilePart(post.postUri))
            }
        }
        return postList
    }

    private fun prepareFilePart(fileUri: Uri): MultipartBody.Part {
        val file = File(fileUri.path)
        //val requestBody = RequestBody.create(MediaType.parse(context?.contentResolver?.getType(fileUri)!!), file)
        val requestBody = RequestBody.create(MediaType.parse("model/form-data"), file)
        return MultipartBody.Part.createFormData("photos", file.name, requestBody)
    }
}