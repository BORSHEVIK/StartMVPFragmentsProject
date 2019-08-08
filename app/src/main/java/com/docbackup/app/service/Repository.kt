package com.docbackup.app.service

import android.content.Context
import com.docbackup.app.abs.LocalSchedulers
import com.docbackup.app.room.DocBackupDatabase
import com.docbackup.app.room.entity.Person
import com.docbackup.app.utils.CSVUtils
import io.reactivex.Single

class Repository(private val context: Context) {

    private val jostensDatabase: DocBackupDatabase

    init {
        jostensDatabase = DocBackupDatabase.getInstance(context)
    }

    fun getAllPersons(): Single<List<Person>> {
        return jostensDatabase.PersonDao().getAll()!!
                .flatMap {
                    jostensDatabase.PersonDao().deleteAll()
                    jostensDatabase.PersonDao().getAll()
                }
                .flatMap { persons ->
                    if (persons == null || persons.size == 0) {
                        jostensDatabase.PersonDao().insertAll(Person.linesCSVToPersonList(CSVUtils.convertCSVFiletoList(context.assets.open("SubjectListExample.csv"))))
                        jostensDatabase.PersonDao().getAll()
                    } else {
                        Single.just(persons)
                    }
                }
                .observeOn(LocalSchedulers.networking())
                .subscribeOn(LocalSchedulers.networking())
    }

    fun updatePerson(person: Person) {
        Single.just(1)
                .observeOn(LocalSchedulers.networking())
                .subscribeOn(LocalSchedulers.networking())
                .subscribe({result ->
                    run {
                        jostensDatabase.PersonDao().update(person)
                    }
                })
    }

    fun insertPerson(person: Person) {
        Single.just(1)
                .observeOn(LocalSchedulers.networking())
                .subscribeOn(LocalSchedulers.networking())
                .subscribe({result ->
                    run {
                        jostensDatabase.PersonDao().insert(person)
                    }
                })
    }

    fun insertAllPersons(persons: List<Person>) {
        Single.just(1)
                .observeOn(LocalSchedulers.networking())
                .subscribeOn(LocalSchedulers.networking())
                .subscribe({result ->
                    run {
                        jostensDatabase.PersonDao().insertAll(persons)
                    }
                })
    }

    fun deleteAllPersons() {
        Single.just(1)
                .observeOn(LocalSchedulers.networking())
                .subscribeOn(LocalSchedulers.networking())
                .subscribe({result ->
                    run {
                        jostensDatabase.PersonDao().deleteAll()
                    }
                })
    }

}