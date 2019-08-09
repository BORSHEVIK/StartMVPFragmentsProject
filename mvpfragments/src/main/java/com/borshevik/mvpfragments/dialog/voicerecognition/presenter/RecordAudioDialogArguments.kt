package com.borshevik.mvpfragments.dialog.message.presenter

import com.borshevik.mvpfragments.abs.dialog.presenter.DialogArguments
import java.io.Serializable

class RecordAudioDialogArguments(parrentTag: String) : DialogArguments(parrentTag), Serializable {

    var question: String = ""
    var filePath: String = ""

}