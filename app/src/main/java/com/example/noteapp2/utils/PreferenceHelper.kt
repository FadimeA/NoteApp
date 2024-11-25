package com.example.noteapp2.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
    private lateinit var sharedPreference:SharedPreferences

    fun unit (context: Context){
        sharedPreference = context.getSharedPreferences("shared",Context.MODE_PRIVATE)
    }

//    var text : String?
//        get() = sharedPreference.getString("text","")
//        set(value:String?) = sharedPreference.edit()?.putString("text",value)!!.apply()

    var isOnBoardShown: Boolean
        get() = sharedPreference.getBoolean ("isOnBoardShown",false)
        set(value: Boolean) = sharedPreference.edit().putBoolean ("isOnBoardShown", value).apply()
}