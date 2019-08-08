package com.docbackup.app.service

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast


class ToastManager(applicationContext: Context) {

    private val applicationContext: Context = applicationContext

    fun showToast(message: String) {

        Handler(Looper.getMainLooper()).post({
            Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
        })
    }
}