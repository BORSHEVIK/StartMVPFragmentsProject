package com.borshevik.mvpfragments.dialog.message

import android.view.View
import com.borshevik.mvpfragments.R
import com.borshevik.mvpfragments.abs.PAbs
import com.borshevik.mvpfragments.abs.dialog.BaseDialogController
import com.borshevik.mvpfragments.abs.model.BaseModel
import com.borshevik.mvpfragments.abs.presenter.DataHolder
import com.borshevik.mvpfragments.dialog.message.presenter.MessageDialogArguments
import com.borshevik.mvpfragments.dialog.message.presenter.MessageDialogPresenter
import com.borshevik.mvpfragments.dialog.message.presenter.MessageDialogPresenterImpl
import com.borshevik.mvpfragments.dialog.message.view.MessageDialogView
import com.borshevik.mvpfragments.dialog.message.view.MessageDialogViewHolder
import com.borshevik.mvpfragments.dialog.message.view.MessageDialogViewImpl

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