package br.com.joaopaulosj.vanhackathon2019.data.local

import android.content.Context
import android.content.SharedPreferences
import br.com.joaopaulosj.vanhackathon2019.Constants.PACKAGE_NAME

object PreferencesHelper {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    private const val SHARED_PREFERENCES_NAME = "$PACKAGE_NAME.SHARED_PREFERENCES"

    private const val PREF_SESSION_COOKIE = "$SHARED_PREFERENCES_NAME.PREF_SESSION_COOKIE"
    private const val PREF_USER_ID = "$SHARED_PREFERENCES_NAME.PREF_USER_ID"
    private const val PREF_BASIC_AUTH = "$SHARED_PREFERENCES_NAME.PREF_BASIC_AUTH"

    var sessionCookie: String
        get() = sharedPreferences.getString(PREF_SESSION_COOKIE, "")
        set(value) = sharedPreferences.edit().putString(PREF_SESSION_COOKIE, value).apply()

    var basicAuth: String
        get() = sharedPreferences.getString(PREF_BASIC_AUTH, "")
        set(value) = sharedPreferences.edit().putString(PREF_BASIC_AUTH, value).apply()

    var userId: String
        get() = sharedPreferences.getString(PREF_USER_ID, "")
        set(value) = sharedPreferences.edit().putString(PREF_USER_ID, value).apply()

    val isLogged: Boolean
        get() = !sessionCookie.isEmpty()

    fun clearBasicAuth() {
        sharedPreferences.edit().putString(PREF_BASIC_AUTH, "").apply()
    }

    fun clearSharedPref() {
        sharedPreferences.edit().clear().apply()
    }
}