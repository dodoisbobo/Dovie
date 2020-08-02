package com.dodosetio.dovie.util

import android.content.Context
import android.content.SharedPreferences

class Preferences (val context: Context){
    companion object{
        const val user_pref = "User_pref"
    }

    var sharedPreferences = context.getSharedPreferences(user_pref, 0)

    fun setValue(key:String, value:String){
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun getValue(key:String) :String?{
        return sharedPreferences.getString(key,"")
    }
}