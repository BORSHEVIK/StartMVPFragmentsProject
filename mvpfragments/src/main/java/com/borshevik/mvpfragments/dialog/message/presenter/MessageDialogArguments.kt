package com.borshevik.mvpfragments.dialog.message.presenter

import com.borshevik.mvpfragments.abs.dialog.presenter.DialogArguments
import java.io.Serializable

class MessageDialogArguments(parrentTag: String) : DialogArguments(parrentTag), Serializable {

    var value: String = ""

}