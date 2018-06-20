package com.robertvargic.githubusersearch.networking

import com.robertvargic.githubusersearch.data.model.Repository
import com.robertvargic.githubusersearch.data.response.SearchResponse
import com.robertvargic.githubusersearch.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users")
    fun searchUsers(@Query("q") searchString: String): Call<SearchResponse>

    @GET("/users/{user}")
    fun getUser(@Path("user") username: String): Call<UserResponse>

    @GET("/users/{user}/repos")
    fun getUserRepos(@Path("user")username: String): Call<ArrayList<Repository>>
}