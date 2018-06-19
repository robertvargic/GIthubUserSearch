package com.robertvargic.githubusersearch

import android.app.Application
import com.robertvargic.githubusersearch.database.UserRoomDatabase

class App : Application() {

    lateinit var userDatabase: UserRoomDatabase

//    constructor(context: Context) : this() {
//       userDatabase = UserRoomDatabase.getDatabaseInstance(context)!!
//    }

    override fun onCreate() {
        super.onCreate()
        userDatabase = UserRoomDatabase.getDatabaseInstance(baseContext)!!
    }

}