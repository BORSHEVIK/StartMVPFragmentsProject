package com.borshevik.mvpfragments.screen.testscreen.view

import com.borshevik.mvpfragments.abs.PresenterProvider
import com.borshevik.mvpfragments.abs.view.BaseViewImpl
import com.borshevik.mvpfragments.room.entity.Person
import com.borshevik.mvpfragments.screen.testscreen.presenter.TestControllerPresenter

class TestControllerViewImpl : BaseViewImpl<TestControllerViewHolder, TestControllerPresenter>, TestControllerView  {

    constructor(myControllerViewHolder: TestControllerViewHolder, presenterProvider: PresenterProvider<TestControllerPresenter>)
            : super(myControllerViewHolder, presenterProvider) {

    }

    override fun updatePersons(persons: MutableList<Person>) {
    }

    override fun setProgressVisibility(visible: Boolean) {
    }

}