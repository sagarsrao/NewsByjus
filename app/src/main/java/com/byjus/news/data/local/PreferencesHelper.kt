package com.byjus.news.data.local

import android.content.Context
import android.content.SharedPreferences
import com.byjus.news.R
import com.byjus.news.injection.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject
constructor(@ApplicationContext context: Context) {

    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(PREF_FILE_NAME.toString(), Context.MODE_PRIVATE)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }


    fun remove(key: String?) {
        preferences.edit().remove(key).apply()
    }

    fun putData(key: String?, data: String?) {

        preferences.edit().putString(key, data).apply()

    }


    fun putDataForInt(key: String?, data: Int?) {

        if (data != null) {
            preferences.edit().putInt(key, data).apply()
        }
    }

    fun getDataForInt(key: String?): Int {

        return preferences.getInt(key, 0)
    }
    fun putDataForBoolean(key: String?, data: Boolean?) {

        if (data != null) {
            preferences.edit().putBoolean(key, data).apply()
        }
    }

    fun getDataForBoolean(key: String?): Boolean? {

        return preferences.getBoolean(key, false)
    }



    companion object {

        val PREF_FILE_NAME = R.string.prefName//PrefName

    }
}