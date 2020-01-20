package com.app.chefbook.ui.registerActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.manager.userManager.IUserManager
import com.app.chefbook.model.serviceModel.requestModel.RegisterUser

class RegisterViewModel (var dataManager: IUserManager) : ViewModel() {

    lateinit var errorResponse: String
    var isAuth = MutableLiveData<Boolean>()


    fun registerUser(registerUser: RegisterUser) {

        dataManager.registerUser(registerUser, object : ServiceCallBack<String> {

            override fun onResponse(response: String) {
                //if StatusCode:200 ise bu işlemler yapılacak şekilde kontrol eklenecek.
                dataManager.saveAuth(response)
                isAuth.postValue(true)
            }

            override fun onError(message: String) {
                errorResponse = message
                isAuth.postValue(false)
            }
        })
    }
}
