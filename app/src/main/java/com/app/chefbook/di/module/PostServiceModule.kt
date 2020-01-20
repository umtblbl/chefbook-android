package com.app.chefbook.di.module

import com.app.chefbook.data.remote.service.postService.PostService
import com.app.chefbook.data.remote.service.postService.IPostService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PostServiceModule {

    @Singleton
    @Provides
    internal fun providesPostService(): IPostService =
        PostService()
}
