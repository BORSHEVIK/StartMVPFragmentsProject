package com.borshevik.mvpfragments.screen.mainscreen.model

import com.borshevik.mvpfragments.abs.Abs
import com.borshevik.mvpfragments.abs.PresenterProvider
import com.borshevik.mvpfragments.abs.model.BaseModelImpl
import com.borshevik.mvpfragments.screen.mainscreen.presenter.MainControllerPresenter

class MainControllerModelImpl : BaseModelImpl<MainControllerPresenter>, MainControllerModel {

    constructor(myControllerPresenterProvider: PresenterProvider<MainControllerPresenter>, abs: Abs) : super(myControllerPresenterProvider, abs) {

    }

}