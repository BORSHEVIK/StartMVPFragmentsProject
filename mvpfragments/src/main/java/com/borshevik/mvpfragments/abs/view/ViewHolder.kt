package com.borshevik.mvpfragments.abs.view

import android.view.View

open abstract class ViewHolder {

    internal val view: View

    constructor(view: View) {
        this.view = view
    }
}