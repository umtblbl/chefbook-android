package com.app.chefbook.ui.loginActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.manager.userManager.IUserManager
import com.app.chefbook.model.serviceModel.requestModel.LoginUser

class LoginViewModel (val userManager: IUserManager) : ViewModel() {

    lateinit var errorResponse: String
    var isAuth = MutableLiveData<Boolean>()

    fun loginUser(loginUser: LoginUser) {

        userManager.loginUser(loginUser, object : ServiceCallBack<String> {

            override fun onResponse(response: String) {
                //if StatusCode:200 ise bu işlemler yapılacak şekilde kontrol eklenecek.
                userManager.saveAuth(response)
                isAuth.postValue(true)
            }

            override fun onError(message: String) {
                errorResponse = message
                isAuth.postValue(false)
            }
        })
    }
}