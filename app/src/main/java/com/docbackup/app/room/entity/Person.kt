package com.docbackup.app.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.docbackup.app.room.entity.Person.Companion.TABLE_NAME
import java.io.Serializable

@Entity(tableName = TABLE_NAME)
data class Person(@PrimaryKey(autoGenerate = true) var id: Long?,
                  @ColumnInfo(name = "psk") var psk: String,
                  @ColumnInfo(name = "first_name") var firstName: String,
                  @ColumnInfo(name = "last_name") var lastName: String,
                  @ColumnInfo(name = "type") var type: String,
                  @ColumnInfo(name = "grade") var grade: String,
                  @ColumnInfo(name = "image_file_name") var imageFileName: String,
                  @ColumnInfo(name = "audio_file_name") var audioFileName: String) : Serializable {

    constructor():this(null, "", "", "", "", "", "", "")

    companion object {

        const val TABLE_NAME = "Person"

        fun lineCSVToPerson(csvLine: List<String>): Person {
            val person: Person = Person()
            person.psk = csvLine[0]
            person.firstName = csvLine[1]
            person.lastName = csvLine[2]
            person.type = csvLine[3]
            person.grade = csvLine[4]
            return person
        }

        fun linesCSVToPersonList(set: LinkedHashSet<List<String>>): List<Person> {
            val persons = mutableListOf<Person>()
            val repsonsArray = set.toList()
            for (i in 2 until repsonsArray.size) {
                persons.add(lineCSVToPerson(repsonsArray[i - 1]))
            }
            return persons
        }
    }
}