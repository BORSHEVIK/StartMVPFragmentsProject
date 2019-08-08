package com.docbackup.app.dialog.message.presenter

import com.docbackup.app.abs.dialog.presenter.DialogArguments
import java.io.Serializable

class RecordAudioDialogArguments(parrentTag: String) : DialogArguments(parrentTag), Serializable {

    var question: String = ""
    var filePath: String = ""

}