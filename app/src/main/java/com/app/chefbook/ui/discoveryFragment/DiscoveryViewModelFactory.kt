package com.app.chefbook.ui.discoveryFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.data.remote.manager.userManager.IUserManager
import com.app.chefbook.data.remote.manager.userManager.UserManager

@Suppress("UNCHECKED_CAST")
class DiscoveryViewModelFactory (val userManager: IUserManager) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DiscoveryViewModel(userManager) as T
    }
}