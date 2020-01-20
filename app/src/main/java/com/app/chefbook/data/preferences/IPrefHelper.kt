package com.app.chefbook.data.preferences

interface IPrefHelper {

    fun saveUdid(udid: String)
    fun getUdid():String?

    fun saveAuth(auth: String)
    fun getAuth():String?
}