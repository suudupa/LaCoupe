package com.suudupa.lacoupe.utility

import android.content.Context
import android.util.Log

object SharedPreferences {

    private const val TAG = "SharedPreferences"
    private const val PREFERENCES_FILE = "app_settings"

    const val isUserLoggedIn = "userLoggedIn"
    const val user = "username"

    fun read(context: Context, settingName: String, defaultValue: String): String {
        return try {
            val sharedPref = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
            sharedPref.getString(settingName, defaultValue).toString()
        } catch (e: NullPointerException) {
            Log.d(TAG, e.message.toString())
            defaultValue
        }

    }

    fun save(context: Context, settingName: String, settingValue: String) {
        val sharedPref = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(settingName, settingValue)
        editor.apply()
    }

    fun readBoolean(context: Context, settingName: String, defaultValue: Boolean): Boolean {
        val sharedPref = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        return  sharedPref.getBoolean(settingName, defaultValue)
    }

    fun saveBoolean(context: Context, settingName: String, settingValue: Boolean){
        val sharedPref = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(settingName, settingValue)
        editor.apply()
    }
}