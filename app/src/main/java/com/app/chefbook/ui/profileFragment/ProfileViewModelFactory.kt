package com.app.chefbook.ui.profileFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.data.remote.manager.userManager.UserManager

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory (val userManager: UserManager) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(userManager) as T
    }
}