package com.docbackup.app.dialog.message.presenter

import com.docbackup.app.abs.dialog.presenter.BaseDialogPresenter
import com.docbackup.app.dialog.message.RecordAudioCallback

interface RecordAudioDialogPresenter : BaseDialogPresenter<RecordAudioCallback> {

    fun recordClick()

}