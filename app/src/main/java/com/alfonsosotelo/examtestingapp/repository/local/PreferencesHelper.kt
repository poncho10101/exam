package com.alfonsosotelo.examtestingapp.repository.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject constructor(var context: Context) {

    companion object {
        const val IS_LOGGED_IN_KEY = "isLoggedIn"
        const val USER_JWT_KEY = "userJWT"
        const val USER_ID_KEY = "userId"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences("testingproj", 0)

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(IS_LOGGED_IN_KEY, false)
        set(value) = prefs.edit().putBoolean(IS_LOGGED_IN_KEY, value).apply()

    var userJWT: String?
        get() = prefs.getString(IS_LOGGED_IN_KEY, null)
        set(value) = prefs.edit().putString(USER_JWT_KEY, value).apply()

    var userId: String?
        get() = prefs.getString(IS_LOGGED_IN_KEY, null)
        set(value) = prefs.edit().putString(USER_ID_KEY, value).apply()
}