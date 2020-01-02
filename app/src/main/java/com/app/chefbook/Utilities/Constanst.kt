package com.app.chefbook.Utilities

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import com.app.chefbook.R

val rootDestinations = setOf(R.id.flowFragment, R.id.discoveryFragment, R.id.profileFragment)

fun getInflateLayout(context:Context, layout:Int): View? {
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    return layoutInflater.inflate(layout, null)
}

fun isValidEmail(target: String?): Boolean {
    return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches())

}

