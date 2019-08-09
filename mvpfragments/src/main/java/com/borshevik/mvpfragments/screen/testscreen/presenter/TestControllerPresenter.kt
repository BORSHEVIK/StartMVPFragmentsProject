package com.borshevik.mvpfragments.screen.testscreen.presenter

import com.borshevik.mvpfragments.abs.presenter.BasePresenter
import com.borshevik.mvpfragments.room.entity.Person

interface TestControllerPresenter : BasePresenter {

    fun personSelected(person: Person)

}