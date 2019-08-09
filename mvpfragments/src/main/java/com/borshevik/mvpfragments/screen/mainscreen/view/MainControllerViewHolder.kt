package com.borshevik.mvpfragments.screen.mainscreen.view

import android.view.View
import android.webkit.WebView
import com.borshevik.mvpfragments.R
import com.borshevik.mvpfragments.abs.view.ViewHolder


class MainControllerViewHolder : ViewHolder {

    val webView: WebView

    constructor(view: View) : super(view) {

        webView = view.findViewById(R.id.web_view)

    }

}