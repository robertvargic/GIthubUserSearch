package com.robertvargic.githubusersearch.networking.base

import com.robertvargic.githubusersearch.networking.ApiService
import retrofit2.Retrofit

abstract class BaseTask(retrofit: Retrofit) {
    var service: ApiService = retrofit.create(ApiService::class.java)
}