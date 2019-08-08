package com.docbackup.app.dialog.message.presenter

import com.docbackup.app.abs.PAbs
import com.docbackup.app.abs.dialog.presenter.BaseDialogPresenterImlp
import com.docbackup.app.abs.model.BaseModel
import com.docbackup.app.abs.presenter.DataHolder
import com.docbackup.app.dialog.message.MessageCallback
import com.docbackup.app.dialog.message.view.MessageDialogView

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