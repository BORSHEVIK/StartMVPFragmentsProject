package com.docbackup.app.screen.testscreen.view

import com.docbackup.app.abs.PresenterProvider
import com.docbackup.app.abs.view.BaseViewImpl
import com.docbackup.app.room.entity.Person
import com.docbackup.app.screen.testscreen.presenter.TestControllerPresenter

class TestControllerViewImpl : BaseViewImpl<TestControllerViewHolder, TestControllerPresenter>, TestControllerView  {

    constructor(myControllerViewHolder: TestControllerViewHolder, presenterProvider: PresenterProvider<TestControllerPresenter>)
            : super(myControllerViewHolder, presenterProvider) {

    }

    override fun updatePersons(persons: MutableList<Person>) {
    }

    override fun setProgressVisibility(visible: Boolean) {
    }

}