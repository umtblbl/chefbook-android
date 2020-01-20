package com.app.chefbook.ui.postInitiatorFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chefbook.data.remote.service.postService.PostService

@Suppress("UNCHECKED_CAST")
class PostInitiatorViewModelFactory(val postService: PostService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostInitiatorViewModel(postService) as T
    }
}