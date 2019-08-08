package com.docbackup.app.screen.testscreen.model

import com.docbackup.app.abs.Abs
import com.docbackup.app.abs.PresenterProvider
import com.docbackup.app.abs.model.BaseModelImpl
import com.docbackup.app.room.entity.Person
import com.docbackup.app.screen.testscreen.presenter.TestControllerPresenter
import io.reactivex.Single

class TestControllerModelImpl : BaseModelImpl<TestControllerPresenter>, TestControllerModel {

    constructor(myControllerPresenterProvider: PresenterProvider<TestControllerPresenter>, abs: Abs) : super(myControllerPresenterProvider, abs) {
    }

    override fun getAllPersons(): Single<List<Person>> {
        return Single.just(listOf())
    }

}