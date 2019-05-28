package com.docbackup.app.abs.dialog.presenter

import com.docbackup.app.abs.Abs
import com.docbackup.app.abs.PAbs
import com.docbackup.app.abs.dialog.BaseDialogEventListener
import com.docbackup.app.abs.model.BaseModel
import com.docbackup.app.abs.presenter.BasePresenterImpl
import com.docbackup.app.abs.presenter.DataHolder
import com.docbackup.app.abs.view.BaseView

abstract open class BaseDialogPresenterImlp<V: BaseView, M: BaseModel, D: DataHolder, A: DialogArguments, L: BaseDialogEventListener>(view: V, model: M, dataHolder: D, arguments: A, abs: PAbs) :
        BasePresenterImpl<V, M, D, A>(view, model, dataHolder, arguments, abs), BaseDialogPresenter<L> {

    internal lateinit var eventListener: L;

    override fun attachEventListener(eventListener: L) {
        this.eventListener = eventListener;
    }

    fun getControllerTag(): String {
        return arguments.controllerTag;
    }
}