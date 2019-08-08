package com.docbackup.app.screen.testscreen.model

import com.docbackup.app.abs.model.BaseModel
import com.docbackup.app.room.entity.Person
import io.reactivex.Single

interface TestControllerModel : BaseModel {

    fun getAllPersons(): Single<List<Person>>

}