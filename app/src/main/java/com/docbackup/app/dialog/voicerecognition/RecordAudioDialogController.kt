package com.docbackup.app.dialog.message

import android.os.Bundle
import android.view.View
import com.docbackup.app.R
import com.docbackup.app.abs.PAbs
import com.docbackup.app.abs.dialog.BaseDialogController
import com.docbackup.app.abs.model.BaseModel
import com.docbackup.app.abs.presenter.DataHolder
import com.docbackup.app.dialog.message.presenter.RecordAudioDialogArguments
import com.docbackup.app.dialog.message.presenter.RecordAudioDialogPresenter
import com.docbackup.app.dialog.message.presenter.RecordAudioDialogPresenterImpl
import com.docbackup.app.dialog.message.view.RecordAudioDialogView
import com.docbackup.app.dialog.message.view.RecordAudioDialogViewHolder
import com.docbackup.app.dialog.message.view.RecordAudioDialogViewImpl

class RecordAudioDialogController : BaseDialogController<RecordAudioDialogViewHolder, RecordAudioDialogView, DataHolder, RecordAudioDialogPresenter, RecordAudioDialogArguments, RecordAudioCallback>() {

    override fun createDataHolder(): DataHolder {
        return DataHolder()
    }

    override fun createViewHolder(view: View): RecordAudioDialogViewHolder {
        return RecordAudioDialogViewHolder(view)
    }

    override fun createView(viewHolder: RecordAudioDialogViewHolder): RecordAudioDialogView {
        return RecordAudioDialogViewImpl(viewHolder, this)
    }

    override fun createPresenter(view: RecordAudioDialogView, model: BaseModel, dataHolder: DataHolder, arguments: RecordAudioDialogArguments, abs: PAbs): RecordAudioDialogPresenter {
        return RecordAudioDialogPresenterImpl(view, model, dataHolder, arguments, abs)
    }

    override fun getViewLayoutId(): Int {
        return R.layout.voice_recognition_dialog
    }

}