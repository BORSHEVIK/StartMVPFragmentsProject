package com.docbackup.app.dialog.message.view

import com.docbackup.app.abs.view.BaseView

interface RecordAudioDialogView : BaseView {

    fun setQuestion(question: String)
    fun setTimerText(time: Int)
    fun setTimerVisibility(visibile: Boolean)
    fun finish()
    fun startRecord()

}