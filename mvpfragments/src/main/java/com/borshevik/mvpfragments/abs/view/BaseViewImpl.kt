package com.borshevik.mvpfragments.abs.view

import com.borshevik.mvpfragments.abs.PresenterProvider
import com.borshevik.mvpfragments.abs.presenter.BasePresenter

open class BaseViewImpl<V : ViewHolder, P : BasePresenter> : BaseView {

    internal val viewHolder: V
    internal val presenterProvider: PresenterProvider<P>

    constructor(viewHolder: V, presenterProvider: PresenterProvider<P>) {
        this.viewHolder = viewHolder
        this.presenterProvider = presenterProvider
    }

}