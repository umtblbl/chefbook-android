package com.app.chefbook.di.PostService

import com.app.chefbook.ui.postInitiatorFragment.PostInitiatorViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PostServiceModule::class])
interface PostServiceComponent {

    fun inject(postInitiatorViewModel: PostInitiatorViewModel)
}