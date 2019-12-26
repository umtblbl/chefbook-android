package com.app.chefbook.Data.Preferences

interface IPrefHelper {

    fun saveUdid(udid: String)
    fun getUdid():String?

    fun saveAuth(auth: String)
    fun getAuth():String?
}