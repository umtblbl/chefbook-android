package com.app.chefbook.DI.DataManager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

val AppCompatActivity.componentActivity: ApplicationComponent
    get() = (application as DaggerApplication).applicationComponent


val Fragment.componentFragment: ApplicationComponent
    get() = ( activity?.application as DaggerApplication).applicationComponent
