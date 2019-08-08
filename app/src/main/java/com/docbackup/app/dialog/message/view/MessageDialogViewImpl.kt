package com.docbackup.app.dialog.message.view

import com.docbackup.app.abs.PresenterProvider
import com.docbackup.app.abs.view.BaseViewImpl
import com.docbackup.app.dialog.message.presenter.MessageDialogPresenter

class MessageDialogViewImpl : BaseViewImpl<MessageDialogViewHolder, MessageDialogPresenter>, MessageDialogView {

    constructor(messageDialogViewHolder: MessageDialogViewHolder, presenterProvider: PresenterProvider<MessageDialogPresenter>)
            : super(messageDialogViewHolder, presenterProvider) {
        viewHolder.okButton.setOnClickListener { presenterProvider.getPresenter().okButtonClick() }
    }

    override fun setMeessage(message: String) {
        viewHolder.messageTextView.setText(message)
    }

}