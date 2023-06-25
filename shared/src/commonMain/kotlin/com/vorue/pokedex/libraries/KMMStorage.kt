package com.vorue.pokedex.libraries


class KMMStorage(val context: SPref) {

    fun getInt(key: String): Int {
        return context.getInt(key)
    }

    fun putInt(key: String, value: Int) {
        context.setInt(key,value)
    }

    fun getString(key: String): String? {
        return context.getString(key)
    }

    fun putString(key: String, value: String) {
        context.setString(key,value)
    }
}