package com.app.chefbook.data.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PrefHelper @Inject constructor(activity: Context) : IPrefHelper {

    private var mPrefs: SharedPreferences
    private var PREF_UDID = "udid"
    private val PREF_AUTH = "authorization"
    private var prefFileName = ""
    private var context: Context

    init {
        mPrefs = activity.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
        this.context = activity
    }

    override fun saveUdid(udid: String) {
        mPrefs.edit().putString(PREF_UDID, udid).apply()
    }

    override fun getUdid(): String? {
        return mPrefs.getString(PREF_UDID, null)
    }

    override fun saveAuth(auth: String) {
        mPrefs.edit().putString(PREF_AUTH, auth).apply()
    }

    override fun getAuth(): String? {
        return mPrefs.getString(PREF_AUTH, null)
    }


}