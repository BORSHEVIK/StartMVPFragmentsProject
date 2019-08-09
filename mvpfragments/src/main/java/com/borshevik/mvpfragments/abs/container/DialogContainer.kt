package com.borshevik.mvpfragments.abs.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.borshevik.mvpfragments.R

class DialogContainer : Container() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_container, container, false);
    }

}