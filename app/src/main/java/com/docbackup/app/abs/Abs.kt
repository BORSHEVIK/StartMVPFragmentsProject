package com.docbackup.app.abs

import android.content.Context
import com.docbackup.app.abs.dialog.BaseDialogEventListener
import com.docbackup.app.service.Navigator
import com.docbackup.app.service.PermissionsService
import com.docbackup.app.service.ToastManager
import java.io.Serializable

interface Abs : Serializable {

    fun getPermissionService(): PermissionsService;
    fun getContext(): Context;

}