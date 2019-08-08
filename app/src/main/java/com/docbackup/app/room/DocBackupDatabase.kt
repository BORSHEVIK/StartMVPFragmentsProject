package com.docbackup.app.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.docbackup.app.room.DocBackupDatabase.Companion.VERSION
import com.docbackup.app.room.dao.PersonDao
import com.docbackup.app.room.entity.Person

@Database(entities = arrayOf(Person::class), version = VERSION)
abstract class DocBackupDatabase : RoomDatabase() {

    companion object {
        const val VERSION = 1
        const val DATABASE_NAME = "DocBackup.db"

        private var INSTANCE: DocBackupDatabase? = null

        fun getInstance(context: Context): DocBackupDatabase {
            if (INSTANCE == null) {
                synchronized(DocBackupDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            DocBackupDatabase::class.java, DATABASE_NAME).build()
                }
            }

            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    abstract fun PersonDao(): PersonDao


}