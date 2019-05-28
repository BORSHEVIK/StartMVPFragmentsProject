package com.docbackup.app.abs.presenter

import android.content.Intent
import com.bluelinelabs.conductor.Controller
import com.docbackup.app.abs.Abs
import com.docbackup.app.abs.PAbs
import com.docbackup.app.abs.model.BaseModel
import com.docbackup.app.abs.view.BaseView

abstract open class BasePresenterImpl<V: BaseView, M: BaseModel, D: DataHolder, A: Arguments> : BasePresenter {

    internal val view: V;
    internal val model: M;
    internal val dataHolder: D;
    internal val arguments: A;
    internal val abs: PAbs;

    constructor(view: V, model: M, dataHolder: D, arguments: A, abs: PAbs) {
        this.view = view;
        this.model = model;
        this.dataHolder = dataHolder;
        this.arguments = arguments;
        this.abs = abs;
    }

    override fun onDestroy() {

    }

    override fun onCreate() {

    }

    override fun onResume() {

    }

    override fun onStop() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //Do nothing
    }

    fun getCurrentController(): Controller {
        return abs.getNavigator().getControllerByTag(arguments.controllerTag)
    }

}