package com.borshevik.mvpfragments.dialog.message

import android.view.View
import com.borshevik.mvpfragments.R
import com.borshevik.mvpfragments.abs.PAbs
import com.borshevik.mvpfragments.abs.dialog.BaseDialogController
import com.borshevik.mvpfragments.abs.model.BaseModel
import com.borshevik.mvpfragments.abs.presenter.DataHolder
import com.borshevik.mvpfragments.dialog.message.presenter.RecordAudioDialogArguments
import com.borshevik.mvpfragments.dialog.message.presenter.RecordAudioDialogPresenter
import com.borshevik.mvpfragments.dialog.message.presenter.RecordAudioDialogPresenterImpl
import com.borshevik.mvpfragments.dialog.message.view.RecordAudioDialogView
import com.borshevik.mvpfragments.dialog.message.view.RecordAudioDialogViewHolder
import com.borshevik.mvpfragments.dialog.message.view.RecordAudioDialogViewImpl

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