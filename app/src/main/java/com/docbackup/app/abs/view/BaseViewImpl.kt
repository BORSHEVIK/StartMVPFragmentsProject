package com.docbackup.app.abs.view

import com.docbackup.app.abs.PresenterProvider
import com.docbackup.app.abs.presenter.BasePresenter

open class BaseViewImpl<V : ViewHolder, P : BasePresenter> : BaseView {

    internal val viewHolder: V
    internal val presenterProvider: PresenterProvider<P>

    constructor(viewHolder: V, presenterProvider: PresenterProvider<P>) {
        this.viewHolder = viewHolder
        this.presenterProvider = presenterProvider
    }

}