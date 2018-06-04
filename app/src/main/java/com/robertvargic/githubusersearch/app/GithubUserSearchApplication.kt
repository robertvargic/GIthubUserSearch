package com.robertvargic.githubusersearch.app

import android.app.Application
import android.content.Context
import com.robertvargic.githubusersearch.database.UserRoomDatabase

class GithubUserSearchApplication : Application() {

    lateinit var userDatabase: UserRoomDatabase

//    constructor(context: Context) : this() {
//       userDatabase = UserRoomDatabase.getDatabaseInstance(context)!!
//    }

    override fun onCreate() {
        super.onCreate()
        userDatabase = UserRoomDatabase.getDatabaseInstance(baseContext)!!
    }

    companion object : SingletonHolder<GithubUserSearchApplication, Context>({ GithubUserSearchApplication() })
}