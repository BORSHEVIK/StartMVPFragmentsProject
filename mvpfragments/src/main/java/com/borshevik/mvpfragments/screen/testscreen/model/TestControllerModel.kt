package com.borshevik.mvpfragments.screen.testscreen.model

import com.borshevik.mvpfragments.abs.model.BaseModel
import com.borshevik.mvpfragments.room.entity.Person
import io.reactivex.Single

interface TestControllerModel : BaseModel {

    fun getAllPersons(): Single<List<Person>>

}