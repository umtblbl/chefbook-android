package com.app.chefbook.UI.MainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.Data.IDataManager

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(val dataManager:IDataManager): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(dataManager) as T
    }
}
