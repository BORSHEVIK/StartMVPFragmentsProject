package com.docbackup.app.abs.model

import com.docbackup.app.abs.Abs
import com.docbackup.app.abs.PresenterProvider
import com.docbackup.app.abs.presenter.BasePresenter

open class BaseModelImpl <P : BasePresenter> : BaseModel {

    internal val presenterProvider: PresenterProvider<P>;
    internal val abs: Abs;

    constructor(presenterProvider: PresenterProvider<P>, abs: Abs) {
        this.presenterProvider = presenterProvider
        this.abs = abs;
    }
}