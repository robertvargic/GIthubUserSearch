package com.robertvargic.githubusersearch.networking.tasks

import android.util.Log
import com.robertvargic.githubusersearch.model.SearchResponse
import com.robertvargic.githubusersearch.networking.base.BaseTask
import com.robertvargic.githubusersearch.networking.base.ServerTask
import com.robertvargic.githubusersearch.networking.base.TaskListener
import retrofit2.*

class SearchForUserTask(retrofit: Retrofit, private val searchString: String) : BaseTask(retrofit), ServerTask<SearchResponse> {
    override fun execute(listener: TaskListener<SearchResponse>) {
        listener.onPreExecute()
        val call = service.searchUsers(searchString)

        call.enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                t?.run { listener.onError(this) }
                Log.e("error", t.toString())
            }

            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                response?.body()?.run { listener.onSucess(this) }
                response?.body()?.run { listener.onError(HttpException(response)) }
            }

        })
    }
}
