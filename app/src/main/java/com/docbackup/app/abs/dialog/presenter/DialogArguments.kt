package com.docbackup.app.abs.dialog.presenter

import com.docbackup.app.abs.presenter.Arguments

open class DialogArguments(parrentTag: String) : Arguments(parrentTag) {

    var title = ""
    var cancelable = true

}