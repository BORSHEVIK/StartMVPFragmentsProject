package com.docbackup.app.dialog.message

import com.docbackup.app.abs.dialog.BaseDialogEventListener

interface MessageCallback : BaseDialogEventListener {

    fun okPressed()

}