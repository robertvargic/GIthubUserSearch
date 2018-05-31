package com.robertvargic.githubusersearch.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users/{q}")
    fun searchUsers(@Query("q:") searchString: String): Call<List<User>>
}