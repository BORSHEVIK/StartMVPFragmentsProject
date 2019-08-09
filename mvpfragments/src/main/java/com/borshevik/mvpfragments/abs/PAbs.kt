package com.borshevik.mvpfragments.abs

import com.borshevik.mvpfragments.abs.dialog.BaseDialogEventListener
import com.borshevik.mvpfragments.service.Navigator
import com.borshevik.mvpfragments.service.ToastManager

interface PAbs : Abs {

    fun getToastManager(): ToastManager
    fun getNavigator(): Navigator
    fun getControllerEventListnerByTag(controllerTag: String): BaseDialogEventListener

}