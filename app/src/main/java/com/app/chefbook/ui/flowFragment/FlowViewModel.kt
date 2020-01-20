package com.app.chefbook.ui.flowFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.manager.userManager.IUserManager
import com.app.chefbook.data.remote.service.postService.IPostService
import com.app.chefbook.data.remote.service.postService.PostService
import com.app.chefbook.model.serviceModel.responseModel.UserFlowPost

class FlowViewModel(val userManager: IUserManager, val postService: IPostService): ViewModel() {

    var flowPostList = MutableLiveData<List<UserFlowPost>>()
    var getFlowPostError = MutableLiveData<String>()
    var flowPostState = MutableLiveData<Boolean>()

    var postLikeCount = MutableLiveData<String>()


    fun getUserFlowPost(){
        Log.e("FlowViewModel-API", "getUserFlowPost")
        flowPostState.postValue(true)

        userManager.getUserFlowPost(object: ServiceCallBack<List<UserFlowPost>> {
            override fun onResponse(response: List<UserFlowPost>) {
                Log.e("FlowViewModel-API", "true")
                flowPostList.postValue(response)
                flowPostState.postValue(false)
            }

            override fun onError(message: String) {
                Log.e("FlowViewModel-API", "false")
                getFlowPostError.postValue(message)
                flowPostState.postValue(false)
            }
        })
    }

    fun sendPostLike(userId: String) {
        postService.sendLike(userId, object :ServiceCallBack<String> {
            override fun onResponse(response: String) {
                postLikeCount.postValue(response)
            }

            override fun onError(message: String) {
                Log.e("FlowViewModel-SendLike", "Error: $message")
            }
        })
    }
}