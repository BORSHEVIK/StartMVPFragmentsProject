package com.borshevik.mvpfragments.activity

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.borshevik.mvpfragments.R
import com.borshevik.mvpfragments.abs.App
import com.borshevik.mvpfragments.abs.PAbs
import com.borshevik.mvpfragments.abs.container.DialogContainer
import com.borshevik.mvpfragments.abs.container.ScreenContainer
import com.borshevik.mvpfragments.abs.dialog.BaseDialogEventListener
import com.borshevik.mvpfragments.abs.dialog.DialogEventListenerStub
import com.borshevik.mvpfragments.abs.dialog.DialogEventProvider
import com.borshevik.mvpfragments.menu.Menu
import com.borshevik.mvpfragments.service.Navigator
import com.borshevik.mvpfragments.service.PermissionsService
import com.borshevik.mvpfragments.service.ToastManager


class MainActivity : AppCompatActivity(), PAbs, PermissionsService.PermissionsCallbacks {

    private lateinit var navigator: Navigator
    private lateinit var toastManager: ToastManager
    private lateinit var permissionsService: PermissionsService
    private lateinit var menu: Menu
    private lateinit var screenContainer: ScreenContainer
    private lateinit var dialogContainer: DialogContainer

    private var pendingPermission: PermissionsService.Permission? = null

    private lateinit var toolBar: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenContainer = supportFragmentManager.findFragmentById(R.id.container) as ScreenContainer
        dialogContainer = supportFragmentManager.findFragmentById(R.id.dialogContainer) as DialogContainer
        toolBar = findViewById(R.id.tab_layout)

        menu = Menu(getContext(), findViewById(R.id.meny_layout) as ViewGroup)
        toastManager = ToastManager(applicationContext)
        permissionsService = PermissionsService(this, toastManager)
        navigator = Navigator(App.INSTANCE.getScreenRouter(), App.INSTANCE.getDialogRouter(),
                screenContainer.childFragmentManager, dialogContainer.childFragmentManager,
                R.id.container, R.id.dialogContainer)

        /*
        val rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe {permis ->
                    Timber.e("Could not access for %s", "")
                }
                */

    }

    override fun onStart() {
        super.onStart()
        permissionsService.setListener(this)
        navigator.setNavigators(App.INSTANCE.getScreenNavigatorHolder(), App.INSTANCE.getDialogNavigatorHolder())
    }

    override fun onStop() {
        permissionsService.removeListener(this)
        navigator.restoreNavigators(App.INSTANCE.getScreenNavigatorHolder(), App.INSTANCE.getDialogNavigatorHolder())
        super.onStop()
    }

    override fun onBackPressed() {
        if (!navigator.onHandleBack()) {
            super.onBackPressed()
        }
    }

    override fun onPermissionRequest(keys: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(this, keys, requestCode)
    }

    override fun onPermissionPending(permission: PermissionsService.Permission) {
        pendingPermission = permission
    }

    override fun onPermissionShowExplanation(explanation: String?): Boolean {
        showPermisionMessage(explanation!!)
        return false
    }

    override fun getNavigator(): Navigator {
        return navigator
    }

    override fun getToastManager(): ToastManager {
        return toastManager
    }

    override fun getPermissionService(): PermissionsService {
        return permissionsService
    }

    //TODO how to move it in navigator without problems or it not needed?
    override fun getControllerEventListnerByTag(controllerTag: String): BaseDialogEventListener {
        //TODO change !! to something more
        val controller: Fragment = screenContainer.childFragmentManager.findFragmentByTag(controllerTag)!!
        return if (controller is DialogEventProvider) controller.provideEvent() else DialogEventListenerStub()
    }

    override fun getContext(): Context {
        return applicationContext
    }

    private fun showPermisionMessage(message: String) {
        val builder: AlertDialog.Builder
        builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.message_title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, { dialog, which ->
                    if (pendingPermission != null) {
                        val p = pendingPermission
                        pendingPermission = null
                        permissionsService.requestPermission(p!!)
                    }
                })
                .setCancelable(false)
                .show()
    }

    override fun getMenu(): Menu {
        return menu
    }

    open fun showToolBar(show: Boolean) {
        toolBar.visibility = if (show) View.VISIBLE else View.GONE
    }

}
