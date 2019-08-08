package com.docbackup.app.dialog.message

import android.os.Bundle
import android.view.View
import com.docbackup.app.R
import com.docbackup.app.abs.PAbs
import com.docbackup.app.abs.dialog.BaseDialogController
import com.docbackup.app.abs.model.BaseModel
import com.docbackup.app.abs.presenter.DataHolder
import com.docbackup.app.dialog.message.presenter.MessageDialogArguments
import com.docbackup.app.dialog.message.presenter.MessageDialogPresenter
import com.docbackup.app.dialog.message.presenter.MessageDialogPresenterImpl
import com.docbackup.app.dialog.message.view.MessageDialogView
import com.docbackup.app.dialog.message.view.MessageDialogViewHolder
import com.docbackup.app.dialog.message.view.MessageDialogViewImpl

class MessageDialogController : BaseDialogController<MessageDialogViewHolder, MessageDialogView, DataHolder, MessageDialogPresenter, MessageDialogArguments, MessageCallback>() {

    override fun createDataHolder(): DataHolder {
        return DataHolder()
    }

    override fun createViewHolder(view: View): MessageDialogViewHolder {
        return MessageDialogViewHolder(view)
    }

    override fun createView(viewHolder: MessageDialogViewHolder): MessageDialogView {
        return MessageDialogViewImpl(viewHolder, this)
    }

    override fun createPresenter(view: MessageDialogView, model: BaseModel, dataHolder: DataHolder, arguments: MessageDialogArguments, abs: PAbs): MessageDialogPresenter {
        return MessageDialogPresenterImpl(view, model, dataHolder, arguments, abs)
    }

    override fun getViewLayoutId(): Int {
        return R.layout.message_dialog
    }

}