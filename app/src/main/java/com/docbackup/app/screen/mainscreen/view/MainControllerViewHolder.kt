package com.docbackup.app.screen.mainscreen.view

import android.view.View
import android.webkit.WebView
import com.docbackup.app.R
import com.docbackup.app.abs.view.ViewHolder


class MainControllerViewHolder : ViewHolder {

    val webView: WebView

    constructor(view: View) : super(view) {

        webView = view.findViewById(R.id.web_view)

    }

}