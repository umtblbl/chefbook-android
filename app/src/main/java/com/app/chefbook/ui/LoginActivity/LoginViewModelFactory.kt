package com.app.chefbook.ui.LoginActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.data.IDataManager

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(val dataManager: IDataManager): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(dataManager) as T
    }
}
