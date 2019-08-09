package com.borshevik.mvpfragments.dialog.message.view

import android.support.v7.widget.AppCompatImageView
import android.view.View
import android.widget.TextView
import com.borshevik.mvpfragments.R
import com.borshevik.mvpfragments.abs.view.ViewHolder

class RecordAudioDialogViewHolder : ViewHolder {

    val questionTextView: TextView
    val timerTextView: TextView
    val recordButton: AppCompatImageView

    constructor(view: View) : super(view) {
        questionTextView = view.findViewById(R.id.question)
        timerTextView = view.findViewById(R.id.timer)
        recordButton = view.findViewById(R.id.record)
    }

}