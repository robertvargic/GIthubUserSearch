package com.robertvargic.githubusersearch.networking

import com.robertvargic.githubusersearch.model.User
import retrofit2.Call

class ApiInteractor {

    var apiService: ApiService

    constructor(apiService: ApiService) {
        this.apiService = apiService
    }


    fun searchUsers(searchQuery: String, callback: Call<List<User>>) {
//        apiService.searchUsers(searchQuery).enqueue(callback)
    }

}