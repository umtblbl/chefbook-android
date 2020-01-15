package com.app.chefbook.ui.profileFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.data.DataManager

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory (val dataManager: DataManager) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(dataManager) as T
    }
}