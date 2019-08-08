package com.docbackup.app.abs

import android.content.Context
import com.docbackup.app.menu.Menu
import com.docbackup.app.service.PermissionsService
import java.io.Serializable

interface Abs : Serializable {

    fun getPermissionService(): PermissionsService
    fun getContext(): Context
    fun getMenu(): Menu

}