package com.docbackup.app.abs

interface AndroidLifeCycle {

    fun onDestroy()
    fun onCreate()
    fun onResume()
    fun onPause()

}