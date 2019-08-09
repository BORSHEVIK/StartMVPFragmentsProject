package com.borshevik.mvpfragments.dialog.message

import com.borshevik.mvpfragments.abs.dialog.BaseDialogEventListener

interface MessageCallback : BaseDialogEventListener {

    fun okPressed()

}