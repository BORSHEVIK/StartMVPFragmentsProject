package com.docbackup.app.abs.dialog.presenter

import com.docbackup.app.abs.dialog.BaseDialogEventListener
import com.docbackup.app.abs.presenter.BasePresenter

interface BaseDialogPresenter<L: BaseDialogEventListener> : BasePresenter {

    fun attachEventListener(eventListener: L)

}