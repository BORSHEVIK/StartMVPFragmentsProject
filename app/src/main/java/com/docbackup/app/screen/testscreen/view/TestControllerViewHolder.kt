package com.docbackup.app.screen.testscreen.view

import android.view.View
import android.widget.TextView
import com.docbackup.app.R
import com.docbackup.app.abs.view.ViewHolder

class TestControllerViewHolder : ViewHolder {

    val messageTextView: TextView;

    constructor(view: View) : super(view) {
        messageTextView = view.findViewById(R.id.message);
    }

}