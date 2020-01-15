package com.app.chefbook.di.DataManager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

val AppCompatActivity.componentActivity: ApplicationComponent
    get() = (application as DaggerApplication).applicationComponent


val Fragment.componentFragment: ApplicationComponent
    get() = ( activity?.application as DaggerApplication).applicationComponent
