package com.app.chefbook.ui.registerActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.data.remote.manager.userManager.IUserManager

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory (val userManager: IUserManager): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(userManager) as T
    }
}