package com.app.chefbook.ui.postInitiatorFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.data.DataManager
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.service.PostService
import com.app.chefbook.data.remote.service.UserService
import com.app.chefbook.model.serviceModel.requestModel.AddPost
import javax.inject.Inject

class PostInitiatorViewModel(val postService: PostService) : ViewModel() {

    var isPostState = MutableLiveData<String>()

    fun sendPost(addPost: AddPost) {
        postService.sendPost(addPost, object : ServiceCallBack<String> {
            override fun onResponse(response: String) {
                isPostState.postValue(response)
            }

            override fun onError(message: String) {
                isPostState.postValue(message)
            }
        })
    }
}