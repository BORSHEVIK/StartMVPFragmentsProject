package com.borshevik.mvpfragments.abs

import android.content.Context
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.text.TextUtils
import android.util.Log
import com.borshevik.mvpfragments.service.PreferenceService
import com.borshevik.mvpfragments.utils.Utils
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class DebugLogTree(context: Context) : Timber.DebugTree() {

    companion object {
        private val LOG = DebugLogTree.javaClass.simpleName

        val LOG_MESSAGE_APPLICATION_STARTED = "Application started"
        val LOG_MESSAGE_CONNECTED_TO_REMOTE_DRIVE = "Connected to remote drive: %s"
        val LOG_MESSAGE_NOT_CONNECTED_TO_REMOTE_DRIVE = "Cannot connect to:{%s}\\{%s} as {%s}"
        val LOG_MESSAGE_PHOTO_TAKEN_TO_FILE = "Photo taken to file: %s"
        val LOG_MESSAGE_PHOTO_NOT_TAKEN_TO_FILE = "Photo not taken to file: %s"
        val LOG_MESSAGE_RECORD_AUDIO_TO_FILE = "Recording to file: %s"
        val LOG_MESSAGE_NOT_RECORD_AUDIO_TO_FILE = "Not recorded to file: %s"
        val LOG_MESSAGE_SUBJECT_SAVED_TO_FILE = "Subject saved to file: %s"
        val LOG_MESSAGE_SUBJECT_NOT_SAVED = "Subject not saved to file: %s"
        val LOG_MESSAGE_FILE_COPIED_TO_DRIVE = "File \"%s\" copied to drive"
        val LOG_MESSAGE_FILE_NOT_COPIED_TO_DRIVE = "File \"%s\" not copied to drive"

    }

    private var timberMessageListener: TimberMessageListener? = null
    private val mainThreadHandler: Handler
    private val preferenceService: PreferenceService

    init {
        preferenceService = PreferenceService(context)
        //Delete file every 7 days
        val logFile = generateFile(Utils.SETUP_LOG_FILE_NAME)
        val fileCreationString = preferenceService.getString(PreferenceService.SHARED_LOG_FILE_CREATION_DATE)
        var logFileCreationDate = 0L;
        if (!TextUtils.isEmpty(fileCreationString)) {
            logFileCreationDate = fileCreationString.toLong()
        }
        if (logFile != null && logFile.exists() && ((Date().time - logFileCreationDate) > TimeUnit.DAYS.toMillis(7))) {
            logFile.delete()
        }
        mainThreadHandler = Handler(Looper.getMainLooper())
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        var priority = priority
        var updatedMessage = message
        if (message.contains("\n")) {
            updatedMessage = message.substringBefore("\n")
        }
        mainThreadHandler.post({
            timberMessageListener?.messageReturned(updatedMessage)
        })
        super.log(priority, tag, message, t)

        if (priority == Log.WARN || priority == Log.ERROR || priority == Log.DEBUG || priority == Log.INFO) {
            try {
                val logTimeStamp = SimpleDateFormat("E MMM dd yyyy 'at' hh:mm:ss:SSS aaa",
                        Locale.getDefault()).format(Date())
                val fileName = Utils.SETUP_LOG_FILE_NAME

                // Create file
                val fileExist = File(File(Utils.getLogFileFolderPath()), fileName).exists()
                val file = generateFile(fileName)
                if (!fileExist) {
                    preferenceService.putString(PreferenceService.SHARED_LOG_FILE_CREATION_DATE, Date().time.toString())
                }
                val debugFile = generateFile(Utils.SETUP_LOG_FILE_NAME_DEBUG)

                // If file created or exists save logs
                if (file != null) {
                    val writer = FileWriter(file, true)
                    writer.append("<p style=\"background:lightgray;\"><strong " + "style=\"background:lightblue;\">")
                            .append(logTimeStamp)
                            .append(" :</strong><strong>")
                            .append(tag)
                            .append("</strong> - ")
                            .append(updatedMessage)
                            .append("</p>")
                    writer.flush()
                    writer.close()
                }

                if (debugFile != null) {
                    val writer = FileWriter(debugFile, true)
                    writer.append("<p style=\"background:lightgray;\"><strong " + "style=\"background:lightblue;\">")
                            .append(logTimeStamp)
                            .append(" :</strong><strong>")
                            .append(tag)
                            .append("</strong> - ")
                            .append(message)
                            .append("</p>")
                    writer.flush()
                    writer.close()
                }
            } catch (e: Exception) {
                Log.e(LOG, "Error while logging into file : $e")
            }
        }

    }

    override fun createStackElementTag(element: StackTraceElement): String? {
        // Add log statements line number to the log
        return super.createStackElementTag(element) + " - " + element.lineNumber
    }
    fun setTimberMessageListener(timberMessageListener: TimberMessageListener) {
        this.timberMessageListener = timberMessageListener
    }

    fun clearLogMessageListener() {
        this.timberMessageListener = null
    }

    interface TimberMessageListener {
        fun messageReturned(message: String)
    }

    /*  Helper method to create file*/
    @Nullable
    private fun generateFile(@NonNull fileName: String): File? {
        var file: File? = null
        if (isExternalStorageAvailable()) {
            val root = File(Utils.getLogFileFolderPath())

            var dirExists = true

            if (!root.exists()) {
                dirExists = root.mkdirs()
            }

            if (dirExists) {
                file = File(root, fileName)
            }
        }
        return file
    }

    /* Helper method to determine if external storage is available*/
    private fun isExternalStorageAvailable(): Boolean {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
    }
}
