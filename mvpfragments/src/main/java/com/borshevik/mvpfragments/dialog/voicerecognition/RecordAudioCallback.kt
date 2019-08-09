package com.borshevik.mvpfragments.dialog.message

import com.borshevik.mvpfragments.abs.dialog.BaseDialogEventListener

interface RecordAudioCallback : BaseDialogEventListener {

    fun audioSaved(filePath: String)

}