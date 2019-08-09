package com.borshevik.mvpfragments.abs.dialog.presenter

import com.borshevik.mvpfragments.abs.PAbs
import com.borshevik.mvpfragments.abs.dialog.BaseDialogEventListener
import com.borshevik.mvpfragments.abs.model.BaseModel
import com.borshevik.mvpfragments.abs.presenter.BasePresenterImpl
import com.borshevik.mvpfragments.abs.presenter.DataHolder
import com.borshevik.mvpfragments.abs.view.BaseView

abstract open class BaseDialogPresenterImlp<V: BaseView, M: BaseModel, D: DataHolder, A: DialogArguments, L: BaseDialogEventListener>(view: V, model: M, dataHolder: D, arguments: A, abs: PAbs) :
        BasePresenterImpl<V, M, D, A>(view, model, dataHolder, arguments, abs), BaseDialogPresenter<L> {

    internal lateinit var eventListener: L

    override fun attachEventListener(eventListener: L) {
        this.eventListener = eventListener
    }

    fun getControllerTag(): String {
        return arguments.controllerTag
    }

    override fun setMenu() {
        //Do nothing
    }
}