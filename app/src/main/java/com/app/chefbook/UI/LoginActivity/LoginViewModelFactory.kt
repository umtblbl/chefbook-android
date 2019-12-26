package com.app.chefbook.UI.LoginActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.Data.IDataManager

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(val dataManager: IDataManager): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(dataManager) as T
    }
}
