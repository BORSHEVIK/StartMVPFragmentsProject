package com.borshevik.mvpfragments.dialog.message.view

import android.view.View
import com.borshevik.mvpfragments.R
import com.borshevik.mvpfragments.abs.PresenterProvider
import com.borshevik.mvpfragments.abs.view.BaseViewImpl
import com.borshevik.mvpfragments.dialog.message.presenter.RecordAudioDialogPresenter

class RecordAudioDialogViewImpl : BaseViewImpl<RecordAudioDialogViewHolder, RecordAudioDialogPresenter>, RecordAudioDialogView {

    constructor(messageDialogViewHolder: RecordAudioDialogViewHolder, presenterProvider: PresenterProvider<RecordAudioDialogPresenter>)
            : super(messageDialogViewHolder, presenterProvider) {
        viewHolder.recordButton.setOnClickListener { presenterProvider.getPresenter().recordClick() }
    }

    override fun setQuestion(question: String) {
        viewHolder.questionTextView.setText(question)
    }

    override fun setTimerVisibility(visibile: Boolean) {
        viewHolder.timerTextView.visibility = if(visibile) View.VISIBLE else View.GONE
        viewHolder.recordButton.isEnabled = !visibile
    }

    override fun setTimerText(time: Int) {
        viewHolder.timerTextView.setText(time.toString())
    }

    override fun finish() {
        setTimerText(0)
        setTimerVisibility(false)
        viewHolder.recordButton.setImageDrawable(viewHolder.recordButton.context.resources.getDrawable(R.drawable.microphone_icon))
    }

    override fun startRecord() {
        viewHolder.recordButton.setImageDrawable(viewHolder.recordButton.context.resources.getDrawable(R.drawable.icon_record_stop))
    }

}