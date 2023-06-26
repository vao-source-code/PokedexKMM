package com.vorue.pokedex.libraries

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

actual typealias SPref = Activity

actual fun SPref.getInt(key: String ) : Int{
    val prefs: SharedPreferences = this.getSharedPreferences("", Context.MODE_PRIVATE)
    return prefs.getInt(key, -1)
}


actual fun SPref.setInt(key: String, value: Int) {
    val prefs: SharedPreferences = this.getSharedPreferences("", Context.MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putInt(key,value)
    editor.apply()
}

actual fun SPref.setString(key: String, value: String) {
    val prefs: SharedPreferences = this.getSharedPreferences("", Context.MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putString(key,value)
    editor.apply()
}

actual fun SPref.getString(key: String) : String?{
    val prefs: SharedPreferences = this.getSharedPreferences("", Context.MODE_PRIVATE)
    return prefs.getString(key, "")
}

actual fun SPref.getBoolean(key: String) : Boolean{
    val prefs: SharedPreferences = this.getSharedPreferences("", Context.MODE_PRIVATE)
    return prefs.getBoolean(key, false)
}

actual fun SPref.setBoolean(key: String, value: Boolean) {
    val prefs: SharedPreferences = this.getSharedPreferences("", Context.MODE_PRIVATE)
    val editor = prefs.edit()
    editor.putBoolean(key,value)
    editor.apply()
}