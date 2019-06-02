package com.snj07.rassignment.db

import android.content.SharedPreferences
import javax.inject.Inject

class RadiusSharedPreferenceManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

}
