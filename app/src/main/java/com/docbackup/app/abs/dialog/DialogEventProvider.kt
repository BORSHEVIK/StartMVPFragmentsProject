package com.docbackup.app.abs.dialog

interface DialogEventProvider {

    fun provideEvent(): BaseDialogEventListener;

}