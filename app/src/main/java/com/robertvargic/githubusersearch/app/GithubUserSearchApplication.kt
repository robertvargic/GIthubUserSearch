package com.robertvargic.githubusersearch.util

import android.app.Application
import android.content.Context
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.networking.RetrofitUtil
import retrofit2.Retrofit
import kotlin.coroutines.experimental.coroutineContext

class GithubUserSearchApplication : Application() {


    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        val retrofit: Retrofit = RetrofitUtil.createRetrofit()
        val userDatabase: UserRoomDatabase = UserRoomDatabase.getDatabaseInstance()!!
    }
}