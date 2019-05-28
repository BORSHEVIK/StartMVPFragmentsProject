package com.docbackup.app.screen.mainscreen.presenter

import com.docbackup.app.abs.presenter.BasePresenter
import com.docbackup.app.dialog.message.MessageCallback

interface MainControllerPresenter : BasePresenter, MessageCallback {

    fun updateButtonClick();
    fun nextButtonClick();
    fun dialogButtonClick();

}