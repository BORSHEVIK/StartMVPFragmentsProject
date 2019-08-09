package com.borshevik.mvpfragments.dialog.message.presenter

import android.media.MediaRecorder
import com.borshevik.mvpfragments.abs.PAbs
import com.borshevik.mvpfragments.abs.dialog.presenter.BaseDialogPresenterImlp
import com.borshevik.mvpfragments.abs.model.BaseModel
import com.borshevik.mvpfragments.abs.presenter.DataHolder
import com.borshevik.mvpfragments.dialog.message.RecordAudioCallback
import com.borshevik.mvpfragments.dialog.message.view.RecordAudioDialogView
import io.reactivex.disposables.Disposable
import java.io.File


class RecordAudioDialogPresenterImpl(view: RecordAudioDialogView, model: BaseModel, dataHolder: DataHolder, arguments: RecordAudioDialogArguments, abs: PAbs) :
        BaseDialogPresenterImlp<RecordAudioDialogView, BaseModel, DataHolder, RecordAudioDialogArguments, RecordAudioCallback>(view, model, dataHolder, arguments, abs), RecordAudioDialogPresenter {

    companion object {
        private const val TIME = 3
        private const val TEMP_FILE_NAME = "/temp_file.amr"
    }

    private var record: MediaRecorder? = null
    private var disposable: Disposable? = null
    private lateinit var tempFile: File
    private var recordStarted = false

    override fun onCreate() {
        super.onCreate()

        tempFile = File(abs.getContext().cacheDir.absolutePath + TEMP_FILE_NAME)
        view.setQuestion(arguments.question)

        recordClick()
    }

    override fun onPause() {
        super.onPause()
        //view.setTimerVisibility(false)
        disposable?.dispose()
        if (recordStarted) {
            finish(true)
        }
    }

    override fun recordClick() {
        //view.setTimerVisibility(true)

        if (!recordStarted) {
            view.startRecord()
            recordStarted = true
            record = MediaRecorder()
            record!!.setAudioSource(MediaRecorder.AudioSource.MIC)
            record!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            record!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            record!!.setOutputFile(tempFile.absolutePath)
            record!!.prepare()
            record!!.start()
        } else {
            finish(true)
        }
    }

    fun finish(save: Boolean) {
        view.finish()
        recordStarted = false

        if (record != null) {
            record!!.stop()
        }

        if (save) {
            val file = File(arguments.filePath)
            if (file.exists()) {
                file.delete()
            }
            tempFile.copyTo(file)
            eventListener.audioSaved(arguments.filePath)
            abs.getNavigator().closeDialogByTag(getControllerTag())
        }

        record = null
    }
}