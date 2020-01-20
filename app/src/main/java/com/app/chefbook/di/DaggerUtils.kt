package com.app.chefbook.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.chefbook.di.ApplicationComponent
import com.app.chefbook.di.DaggerApplication

val AppCompatActivity.componentActivity: ApplicationComponent
    get() = (application as DaggerApplication).applicationComponent


val Fragment.componentFragment: ApplicationComponent
    get() = ( activity?.application as DaggerApplication).applicationComponent


