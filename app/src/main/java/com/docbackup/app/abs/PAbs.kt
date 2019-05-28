package com.docbackup.app.abs

import android.content.Context
import com.docbackup.app.abs.dialog.BaseDialogEventListener
import com.docbackup.app.service.Navigator
import com.docbackup.app.service.ToastManager

interface PAbs : Abs {

    fun getToastManager(): ToastManager;
    fun getNavigator(): Navigator;
    fun getControllerEventListnerByTag(controllerTag: String): BaseDialogEventListener;

}