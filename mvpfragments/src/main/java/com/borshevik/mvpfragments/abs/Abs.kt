package com.borshevik.mvpfragments.abs

import android.content.Context
import com.borshevik.mvpfragments.menu.Menu
import com.borshevik.mvpfragments.service.PermissionsService
import java.io.Serializable

interface Abs : Serializable {

    fun getPermissionService(): PermissionsService
    fun getContext(): Context
    fun getMenu(): Menu

}