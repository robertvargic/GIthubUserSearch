package com.robertvargic.githubusersearch.networking.tasks

import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.networking.base.BaseTask
import com.robertvargic.githubusersearch.networking.base.ServerTask
import com.robertvargic.githubusersearch.networking.base.TaskListener
import retrofit2.*

class GetUserTask(retrofit: Retrofit, private val userName: String) : BaseTask(retrofit), ServerTask<User> {
    override fun execute(listener: TaskListener<User>) {
        listener.onPreExecute()
        val call = service.getUser(userName)

        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                t?.run { listener.onError(this) }
            }

            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                response?.body()?.run { listener.onSucess(this) }
                response?.body()?.run { listener.onError(HttpException(response)) }
            }

        })
    }
}