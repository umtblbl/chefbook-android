package com.app.chefbook.ui.MainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.data.IDataManager

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(val dataManager:IDataManager): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(dataManager) as T
    }
}
