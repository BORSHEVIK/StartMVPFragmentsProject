package com.docbackup.app.dialog.message

import com.docbackup.app.abs.dialog.BaseDialogEventListener

interface RecordAudioCallback : BaseDialogEventListener {

    fun audioSaved(filePath: String)

}