package com.borshevik.mvpfragments.utils

import android.media.MediaPlayer
import timber.log.Timber

object AudioUtils {

    fun playAudio(filePath: String) {
        val mp = MediaPlayer()

        try {
            mp.setDataSource(filePath)
            mp.prepare()
            mp.start()
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

}