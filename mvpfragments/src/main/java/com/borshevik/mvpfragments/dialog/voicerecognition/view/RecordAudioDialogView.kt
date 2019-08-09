package com.borshevik.mvpfragments.dialog.message.view

import com.borshevik.mvpfragments.abs.view.BaseView

interface RecordAudioDialogView : BaseView {

    fun setQuestion(question: String)
    fun setTimerText(time: Int)
    fun setTimerVisibility(visibile: Boolean)
    fun finish()
    fun startRecord()

}