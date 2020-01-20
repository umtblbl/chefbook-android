package com.app.chefbook.ui.flowFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.data.remote.manager.userManager.IUserManager
import com.app.chefbook.data.remote.service.postService.IPostService
import com.app.chefbook.data.remote.service.postService.PostService

@Suppress("UNCHECKED_CAST")
class FlowViewModelFactory (val userManager: IUserManager, val postService: IPostService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FlowViewModel(userManager, postService) as T
    }
}