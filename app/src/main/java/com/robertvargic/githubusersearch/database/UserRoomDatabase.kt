package com.robertvargic.githubusersearch.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.robertvargic.githubusersearch.data.model.Repository
import com.robertvargic.githubusersearch.data.model.User

@Database(entities = arrayOf(User::class, Repository::class), version = 1)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabaseInstance(context: Context?): UserRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(UserRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context!!.applicationContext,
                            UserRoomDatabase::class.java, "user.db")
//                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabaseInstance() {
            INSTANCE = null
        }
    }
}