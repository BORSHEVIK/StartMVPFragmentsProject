package com.borshevik.mvpfragments.screen.testscreen.model

import com.borshevik.mvpfragments.abs.Abs
import com.borshevik.mvpfragments.abs.PresenterProvider
import com.borshevik.mvpfragments.abs.model.BaseModelImpl
import com.borshevik.mvpfragments.room.entity.Person
import com.borshevik.mvpfragments.screen.testscreen.presenter.TestControllerPresenter
import io.reactivex.Single

class TestControllerModelImpl : BaseModelImpl<TestControllerPresenter>, TestControllerModel {

    constructor(myControllerPresenterProvider: PresenterProvider<TestControllerPresenter>, abs: Abs) : super(myControllerPresenterProvider, abs) {
    }

    override fun getAllPersons(): Single<List<Person>> {
        return Single.just(listOf())
    }

}