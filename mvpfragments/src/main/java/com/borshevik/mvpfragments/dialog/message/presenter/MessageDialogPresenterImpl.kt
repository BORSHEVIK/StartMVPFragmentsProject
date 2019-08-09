package com.borshevik.mvpfragments.dialog.message.presenter

import com.borshevik.mvpfragments.abs.PAbs
import com.borshevik.mvpfragments.abs.dialog.presenter.BaseDialogPresenterImlp
import com.borshevik.mvpfragments.abs.model.BaseModel
import com.borshevik.mvpfragments.abs.presenter.DataHolder
import com.borshevik.mvpfragments.dialog.message.MessageCallback
import com.borshevik.mvpfragments.dialog.message.view.MessageDialogView

class MessageDialogPresenterImpl(view: MessageDialogView, model: BaseModel, dataHolder: DataHolder, arguments: MessageDialogArguments, abs: PAbs) :
        BaseDialogPresenterImlp<MessageDialogView, BaseModel, DataHolder, MessageDialogArguments, MessageCallback>(view, model, dataHolder, arguments, abs), MessageDialogPresenter {

    override fun onResume() {
        super.onResume()
        view.setMeessage(arguments.value)
    }

    override fun okButtonClick() {
        abs.getNavigator().closeDialogByTag(getControllerTag())
        eventListener.okPressed()
    }
}