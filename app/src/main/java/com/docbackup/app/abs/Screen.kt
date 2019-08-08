package com.docbackup.app.abs

interface Screen {

    companion object {
        val SCREEN_ARGUMENTS = "SCREEN_ARGUMENTS"

        //Screens
        val SCREEN_MAIN: Int = 1

        val SCREEN_TEST: Int = 2

        val SCREEN_SET_INFO: Int = 3

        val SCREEN_SET_SERVER_PARAMS: Int = 4

        val SCREEN_LIST_OF_SUBJECTS: Int = 5

        val SCREE_SPLASH: Int = 6

        //Dialogs
        val DIALOG_STUB: Int = -1

        val DIALOG_MESSAGE: Int = 1

        val DIALOG_EDIT_TEXT: Int = 2

        val DIALOG_RECORD_AUDIO: Int = 3

        val DIALOG_SCHOOL_PERSONS_LIST: Int = 4

        val DIALOG_EDIT_CONFIG_FILE: Int = 5

        val DIALOG_LOG: Int = 6
    }

}