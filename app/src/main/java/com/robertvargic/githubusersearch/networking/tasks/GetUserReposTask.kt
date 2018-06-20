package com.robertvargic.githubusersearch.networking.tasks

import android.util.Log
import com.robertvargic.githubusersearch.data.model.Repository
import com.robertvargic.githubusersearch.networking.base.BaseTask
import com.robertvargic.githubusersearch.networking.base.ServerTask
import com.robertvargic.githubusersearch.networking.base.TaskListener
import retrofit2.*

class GetUserReposTask(retrofit: Retrofit?, private val userName: String) : BaseTask(retrofit), ServerTask<ArrayList<Repository>> {
    override fun execute(listener: TaskListener<ArrayList<Repository>>) {
        listener.onPreExecute()
        val call = service.getUserRepos(userName)

        call.enqueue(object : Callback<ArrayList<Repository>> {
            override fun onFailure(call: Call<ArrayList<Repository>>?, t: Throwable?) {
                t?.run { listener.onError(this)
                Log.e("error", t.toString())
                }
            }

            override fun onResponse(call: Call<ArrayList<Repository>>?, response: Response<ArrayList<Repository>>?) {
                response?.body()?.run { listener.onSucess(this)
                    Log.e("body", response?.body()?.toString())}
                response?.body()?.run { listener.onError(HttpException(response))
                    Log.e("body", response?.body()?.toString())}
            }

        })
    }
}