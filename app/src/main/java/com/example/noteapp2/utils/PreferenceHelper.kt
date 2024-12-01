package com.example.noteapp2.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
    private lateinit var sharedPreference:SharedPreferences

    fun init (context: Context){
        sharedPreference = context.getSharedPreferences("shared",Context.MODE_PRIVATE)
    }
    var text: Boolean
        get() = sharedPreference.getBoolean("text", false)
        set(value) = sharedPreference.edit().putBoolean("text", value).apply()

    fun setOnBoardingCompleted(b: Boolean) {
        sharedPreference.edit().putBoolean("onBoardingCompleted", text).apply()
    }

    fun isOnBoardingCompleted(): Boolean {
        return sharedPreference.getBoolean("onBoardingCompleted", true)
    }

    fun isLinearLayout(): Boolean {
        return sharedPreference.getBoolean("isLinearLayout", true)
    }
    fun setLinearLayout(isLinearLayout: Boolean) {
        sharedPreference.edit().putBoolean("isLinearLayout", isLinearLayout).apply()
    }
    fun setRegistered(isRegistered: Boolean) {
        sharedPreference.edit().putBoolean("isRegistered", isRegistered).apply()
    }
    fun isRegistered(): Boolean {
        return sharedPreference.getBoolean("isRegistered", false)
    }

}