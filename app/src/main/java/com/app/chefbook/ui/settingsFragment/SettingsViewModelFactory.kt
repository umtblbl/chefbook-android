package com.app.chefbook.ui.settingsFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.data.remote.manager.userManager.UserManager

@Suppress("UNCHECKED_CAST")
class SettingsViewModelFactory (val userManager: UserManager) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingsViewModel(userManager) as T
    }
}