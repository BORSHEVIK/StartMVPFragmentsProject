package com.borshevik.mvpfragments.abs.dialog.presenter

import com.borshevik.mvpfragments.abs.presenter.Arguments

open class DialogArguments(parrentTag: String) : Arguments(parrentTag) {

    var title = ""
    var cancelable = true

}