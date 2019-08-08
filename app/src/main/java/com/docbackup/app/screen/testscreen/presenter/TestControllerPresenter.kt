package com.docbackup.app.screen.testscreen.presenter

import com.docbackup.app.abs.presenter.BasePresenter
import com.docbackup.app.room.entity.Person

interface TestControllerPresenter : BasePresenter {

    fun personSelected(person: Person)

}