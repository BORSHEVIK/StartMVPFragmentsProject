package com.borshevik.mvpfragments.dialog.message.view

import android.view.View
import android.widget.TextView
import com.borshevik.mvpfragments.R
import com.borshevik.mvpfragments.abs.view.ViewHolder

class MessageDialogViewHolder : ViewHolder {

    val messageTextView: TextView
    val okButton: TextView

    constructor(view: View) : super(view) {
        messageTextView = view.findViewById(R.id.message)
        okButton = view.findViewById(R.id.button)
    }

}