package com.app.chefbook.ui.settingsFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.data.DataManager

@Suppress("UNCHECKED_CAST")
class SettingsViewModelFactory (val dataManager: DataManager) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingsViewModel(dataManager) as T
    }
}