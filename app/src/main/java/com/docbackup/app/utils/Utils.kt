package com.docbackup.app.utils

import android.os.Environment
import com.docbackup.app.model.ServerParams
import com.docbackup.app.room.entity.Person
import org.json.JSONObject
import java.io.File
import java.io.InputStream
import java.util.*

object Utils {

    private const val FOLDER_NAME = "/Jpix/"
    const val SETUP_FOLDER = "/Jpix_settings"
    const val SETUP_FILE_NAME = SETUP_FOLDER + "/jpix_setting.json"
    const val SETUP_SUBJECTS_FILE_NAME = SETUP_FOLDER + "/PhotoDayContractInfo.xml"
    const val SETUP_SUBJECTS_VENUE_FILE_NAME = SETUP_FOLDER + "/PhotoDayVenueSelections.xml"
    const val SETUP_LOG_FILE_NAME = "jpix_logs.log"
    const val SETUP_LOG_FILE_NAME_DEBUG = "jpix_logs_debug.log"

    const val DAFAULT_SERVER_NAME = "192.168.0.1"
    const val DEFAULT_FOLDER_NAME = "Volume(sda1)"
    const val DEFAULT_USERNAME = "JostensPIX"
    const val DEFAULT_PASSWORD = "JpixRules!"

    const val FORMAT_JPEG = ".jpeg"
    const val FORAMT_AMR = ".amr"
    const val FORAMT_XML = ".xml"
    const val WITHOUT_PSK_FILE_ADDITIONAL = "(without_psk)"

    private const val SERVER_NAME_PARAM = "server_name"
    private const val FOLDER_NAME_PARAM = "folder_name"
    private const val USERNAME_PARAM = "username"
    private const val PASSWORD_PARAM = "password"


    fun buildImageFileNameFromPerson(person: Person): String {
        return person.lastName + "_" + person.firstName + FORMAT_JPEG
    }

    fun buildAudioFileNameFromPerson(person: Person): String {
        return person.lastName + "_" + person.firstName + FORAMT_AMR
    }

    fun buildImageFileNameFromGUID(PSK: String): String  {
        return PSK + ".jpeg"
    }

    fun buildAudioFileNameFromGUID(PSK: String): String  {
        return PSK + ".amr"
    }

    fun buildXMLFileNameFromGUID(PSK: String): String {
        return PSK + ".xml"
    }

    fun buildImageFileTempName(GUID: String): String  {
        return GUID + WITHOUT_PSK_FILE_ADDITIONAL + ".jpeg"
    }

    fun buildAudioFileTempName(GUID: String): String  {
        return GUID + WITHOUT_PSK_FILE_ADDITIONAL + ".amr"
    }

    fun tempImagePath(): String {
        return Environment.getExternalStorageDirectory().absolutePath + "/temp_image" + FORMAT_JPEG
    }

    fun tempAudioPath(): String {
        return Environment.getExternalStorageDirectory().absolutePath + "/temp_audio" + FORAMT_AMR
    }

    fun buildFullFilePathFromFileName(fileName: String): String {
        return getPathToFolder() + fileName
    }

    fun getPathToFolder(): String {
        return Environment.getExternalStorageDirectory().absolutePath + FOLDER_NAME
    }

    fun getLogFileFolderPath(): String {
        return Environment.getExternalStorageDirectory().absolutePath + SETUP_FOLDER
    }

    fun convertJsonObjectToModel(json: JSONObject): ServerParams {

        return ServerParams(if (json.has(SERVER_NAME_PARAM)) json.getString(SERVER_NAME_PARAM) else DAFAULT_SERVER_NAME,
                if (json.has(FOLDER_NAME_PARAM)) json.getString(FOLDER_NAME_PARAM) else DEFAULT_FOLDER_NAME,
                if (json.has(USERNAME_PARAM)) json.getString(USERNAME_PARAM) else DEFAULT_USERNAME,
                if (json.has(PASSWORD_PARAM)) json.getString(PASSWORD_PARAM) else DEFAULT_PASSWORD)
    }

    fun converServerParamsToJsonObject(serverParams: ServerParams): JSONObject {
        val json = JSONObject()
        json.put(SERVER_NAME_PARAM, serverParams.serverIp)
        json.put(FOLDER_NAME_PARAM, serverParams.folderName)
        json.put(USERNAME_PARAM, serverParams.username)
        json.put(PASSWORD_PARAM, serverParams.password)
        return json
    }

    fun generateGUID(): String {
        return UUID.randomUUID().toString() + Date().time
    }
}

fun File.copyInputStreamToFile(inputStream: InputStream) {
    inputStream.use { input ->
        this.outputStream().use { fileOut ->
            input.copyTo(fileOut)
        }
    }
}