package com.borshevik.mvpfragments.abs.model

import com.borshevik.mvpfragments.abs.Abs
import com.borshevik.mvpfragments.abs.PresenterProvider
import com.borshevik.mvpfragments.abs.presenter.BasePresenter

open class BaseModelImpl <P : BasePresenter> : BaseModel {

    internal val presenterProvider: PresenterProvider<P>
    internal val abs: Abs

    constructor(presenterProvider: PresenterProvider<P>, abs: Abs) {
        this.presenterProvider = presenterProvider
        this.abs = abs
    }
}