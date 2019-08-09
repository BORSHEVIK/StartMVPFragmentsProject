package com.borshevik.mvpfragments.screen.testscreen.view

import com.borshevik.mvpfragments.abs.view.BaseView
import com.borshevik.mvpfragments.room.entity.Person

interface TestControllerView : BaseView {

    fun updatePersons(persons: MutableList<Person>)
    fun setProgressVisibility(visible: Boolean)

}