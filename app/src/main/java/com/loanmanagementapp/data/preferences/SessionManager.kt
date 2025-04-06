package com.loanmanagementapp.data.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SessionManager @Inject constructor(@ApplicationContext context: Context) {
    companion object {
        const val USER_PREFS = "user_prefs"
        const val IS_LOGGED_IN = "is_logged_in"
        const val USERNAME = "username"
    }

    private val prefs = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)

    fun saveLoginState(isLoggedIn: Boolean, username: String? = null) {
        prefs.edit().apply {
            putBoolean(IS_LOGGED_IN, isLoggedIn)
            username?.let { putString(USERNAME, it) }
        }.apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(IS_LOGGED_IN, false)
    }

    fun getUsername(): String? {
        return prefs.getString(USERNAME, null)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }
}