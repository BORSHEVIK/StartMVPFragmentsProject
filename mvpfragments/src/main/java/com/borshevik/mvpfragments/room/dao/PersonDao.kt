package com.borshevik.mvpfragments.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.borshevik.mvpfragments.room.entity.Person
import io.reactivex.Single

@Dao
interface PersonDao {

    @Query("SELECT * from " + Person.TABLE_NAME)
    fun getAll(): Single<List<Person>>

    @Insert(onConflict = REPLACE)
    fun insert(person: Person)

    @Insert(onConflict = REPLACE)
    fun insertAll(persons: List<Person>)

    @Query("DELETE from " + Person.TABLE_NAME)
    fun deleteAll()

    @Update
    fun update(person: Person)

}