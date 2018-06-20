package com.robertvargic.githubusersearch.networking

import com.robertvargic.githubusersearch.model.Repository
import com.robertvargic.githubusersearch.model.response.SearchResponse
import com.robertvargic.githubusersearch.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users")
    fun searchUsers(@Query("q") searchString: String): Call<SearchResponse>

    @GET("/users/{user}")
    fun getUser(@Path("user") username: String): Call<User>

    @GET("/users/{user}/repos")
    fun getUserRepos(@Path("user")username: String): Call<ArrayList<Repository>>
}