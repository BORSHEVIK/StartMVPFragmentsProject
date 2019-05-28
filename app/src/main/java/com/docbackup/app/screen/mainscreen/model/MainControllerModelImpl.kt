package com.docbackup.app.screen.mainscreen.model

import com.docbackup.app.abs.Abs
import com.docbackup.app.abs.PresenterProvider
import com.docbackup.app.abs.model.BaseModelImpl
import com.docbackup.app.screen.mainscreen.presenter.MainControllerPresenter

class MainControllerModelImpl : BaseModelImpl<MainControllerPresenter>, MainControllerModel {

    constructor(myControllerPresenterProvider: PresenterProvider<MainControllerPresenter>, abs: Abs) : super(myControllerPresenterProvider, abs) {

    }

}