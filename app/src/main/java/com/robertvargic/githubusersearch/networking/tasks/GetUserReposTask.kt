package com.robertvargic.githubusersearch.networking.tasks

import android.util.Log
import com.robertvargic.githubusersearch.data.response.RepositoryResponse
import com.robertvargic.githubusersearch.networking.base.BaseTask
import com.robertvargic.githubusersearch.networking.base.ServerTask
import com.robertvargic.githubusersearch.networking.base.TaskListener
import retrofit2.*

class GetUserReposTask(retrofit: Retrofit?, private val userName: String) : BaseTask(retrofit), ServerTask<ArrayList<RepositoryResponse>> {
    override fun execute(listener: TaskListener<ArrayList<RepositoryResponse>>) {
        listener.onPreExecute()
        val call = service.getUserRepos(userName)

        call.enqueue(object : Callback<ArrayList<RepositoryResponse>> {
            override fun onFailure(call: Call<ArrayList<RepositoryResponse>>?, t: Throwable?) {
                t?.run { listener.onError(this)
                Log.e("error", t.toString())
                }
            }

            override fun onResponse(call: Call<ArrayList<RepositoryResponse>>?, response: Response<ArrayList<RepositoryResponse>>?) {
                response?.body()?.run { listener.onSucess(this)
                    Log.e("body", response?.body()?.toString())}
                response?.body()?.run { listener.onError(HttpException(response))
                    Log.e("body", response?.body()?.toString())}
            }

        })
    }
}