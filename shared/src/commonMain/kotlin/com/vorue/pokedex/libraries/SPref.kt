package com.vorue.pokedex.libraries

expect class SPref

expect fun SPref.getString(key: String) : String?
expect fun SPref.setString(key: String, value: String)

expect fun SPref.getInt(key: String) : Int
expect fun SPref.setInt(key: String, value: Int)
expect fun SPref.getBoolean(key: String) : Boolean

expect fun SPref.setBoolean(key: String, value: Boolean)