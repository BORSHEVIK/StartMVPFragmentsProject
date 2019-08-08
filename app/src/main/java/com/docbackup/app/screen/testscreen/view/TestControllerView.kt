package com.docbackup.app.screen.testscreen.view

import com.docbackup.app.abs.view.BaseView
import com.docbackup.app.room.entity.Person

interface TestControllerView : BaseView {

    fun updatePersons(persons: MutableList<Person>)
    fun setProgressVisibility(visible: Boolean)

}