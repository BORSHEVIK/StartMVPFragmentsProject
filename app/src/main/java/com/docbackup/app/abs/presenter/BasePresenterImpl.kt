package com.docbackup.app.abs.presenter

import android.content.Intent
import android.support.v4.app.Fragment
import com.docbackup.app.abs.PAbs
import com.docbackup.app.abs.model.BaseModel
import com.docbackup.app.abs.view.BaseView
import com.docbackup.app.menu.MenuCallback

abstract open class BasePresenterImpl<V: BaseView, M: BaseModel, D: DataHolder, A: Arguments> : BasePresenter {

    internal val view: V
    internal val model: M
    internal val dataHolder: D
    internal val arguments: A
    internal val abs: PAbs

    constructor(view: V, model: M, dataHolder: D, arguments: A, abs: PAbs) {
        this.view = view
        this.model = model
        this.dataHolder = dataHolder
        this.arguments = arguments
        this.abs = abs
    }

    override fun onDestroy() {

    }

    override fun onCreate() {
        setMenu()
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //Do nothing
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        //DO nothing
    }

    open internal fun setMenu() {
        abs.getMenu().setMenu(arrayListOf(), object: MenuCallback {
            override fun menuItemClick(itemID: Int) {
                //Not implemented
            }
        })
    }

    fun getCurrentController(): Fragment {
        return getCurrentController(false)
    }

    fun getCurrentController(isDialog: Boolean): Fragment {
        return abs.getNavigator().getControllerByTag(arguments.controllerTag, isDialog)
    }

}