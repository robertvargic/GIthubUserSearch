package com.robertvargic.githubusersearch.networking

import com.robertvargic.githubusersearch.model.SearchResponse
import com.robertvargic.githubusersearch.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users")
    fun searchUsers(@Query("q") searchString: String): Call<SearchResponse>

    @GET("/search/{user}")
    fun getUser(@Query("user") username: String): Call<User>
}