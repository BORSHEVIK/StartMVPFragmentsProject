package com.borshevik.mvpfragments.abs.dialog.presenter

import com.borshevik.mvpfragments.abs.dialog.BaseDialogEventListener
import com.borshevik.mvpfragments.abs.presenter.BasePresenter

interface BaseDialogPresenter<L: BaseDialogEventListener> : BasePresenter {

    fun attachEventListener(eventListener: L)

}