package com.docbackup.app.service

import android.content.Context
import android.content.Context.MODE_PRIVATE

class PreferenceService(private val context: Context) {

    companion object {

        private val SHARED_NAME = "Jostens preferences"

        val SHARED_IP = "SHARED_IP"
        val SHARED_REMOTE_FOLDER_NAME = "SHARED_REMOTE_FOLDER_NAME"
        val SHARED_USERNAME = "SHARED_USERNAME"
        val SHARED_PASSWORD = "SHARED_PASSWORD"

        val SHARED_LOG_FILE_CREATION_DATE = "SHARED_LOG_FILE_CREATION_DATE"

    }

    fun putString(key: String, value: String) {
        val editor = context.getSharedPreferences(SHARED_NAME, MODE_PRIVATE).edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getString(key: String): String {
        return context.getSharedPreferences(SHARED_NAME, MODE_PRIVATE).getString(key, "")
    }

}