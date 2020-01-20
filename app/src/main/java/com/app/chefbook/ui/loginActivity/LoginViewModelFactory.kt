package com.app.chefbook.ui.loginActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.data.remote.manager.userManager.IUserManager

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(val userManager: IUserManager): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(userManager) as T
    }
}
