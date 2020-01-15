package com.app.chefbook.ui.RegisterActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.data.IDataManager

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory (val dataManager: IDataManager): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(dataManager) as T
    }
}