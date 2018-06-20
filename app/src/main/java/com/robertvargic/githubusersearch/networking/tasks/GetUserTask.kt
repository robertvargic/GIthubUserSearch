package com.robertvargic.githubusersearch.networking.tasks

import com.robertvargic.githubusersearch.data.response.UserResponse
import com.robertvargic.githubusersearch.networking.base.BaseTask
import com.robertvargic.githubusersearch.networking.base.ServerTask
import com.robertvargic.githubusersearch.networking.base.TaskListener
import retrofit2.*

class GetUserTask(retrofit: Retrofit?, private val userName: String) : BaseTask(retrofit), ServerTask<UserResponse> {
    override fun execute(listener: TaskListener<UserResponse>) {
        listener.onPreExecute()
        val call = service.getUser(userName)

        call.enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
                t?.run { listener.onError(this) }
            }

            override fun onResponse(call: Call<UserResponse>?, response: Response<UserResponse>?) {
                response?.body()?.run { listener.onSucess(this) }
                response?.body()?.run { listener.onError(HttpException(response)) }
            }

        })
    }
}