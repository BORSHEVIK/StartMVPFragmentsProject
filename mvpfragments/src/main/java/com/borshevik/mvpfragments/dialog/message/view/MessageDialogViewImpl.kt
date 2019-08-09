package com.borshevik.mvpfragments.dialog.message.view

import com.borshevik.mvpfragments.abs.PresenterProvider
import com.borshevik.mvpfragments.abs.view.BaseViewImpl
import com.borshevik.mvpfragments.dialog.message.presenter.MessageDialogPresenter

class MessageDialogViewImpl : BaseViewImpl<MessageDialogViewHolder, MessageDialogPresenter>, MessageDialogView {

    constructor(messageDialogViewHolder: MessageDialogViewHolder, presenterProvider: PresenterProvider<MessageDialogPresenter>)
            : super(messageDialogViewHolder, presenterProvider) {
        viewHolder.okButton.setOnClickListener { presenterProvider.getPresenter().okButtonClick() }
    }

    override fun setMeessage(message: String) {
        viewHolder.messageTextView.setText(message)
    }

}