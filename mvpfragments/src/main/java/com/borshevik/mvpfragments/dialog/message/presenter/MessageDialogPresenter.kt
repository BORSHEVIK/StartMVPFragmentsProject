package com.borshevik.mvpfragments.dialog.message.presenter

import com.borshevik.mvpfragments.abs.dialog.presenter.BaseDialogPresenter
import com.borshevik.mvpfragments.dialog.message.MessageCallback

interface MessageDialogPresenter : BaseDialogPresenter<MessageCallback> {

    fun okButtonClick()

}