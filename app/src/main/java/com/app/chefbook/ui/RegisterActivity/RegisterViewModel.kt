package com.app.chefbook.ui.RegisterActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.Data.IDataManager
import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.model.serviceModel.requestModel.RegisterUser

class RegisterViewModel (var dataManager: IDataManager) : ViewModel() {

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
