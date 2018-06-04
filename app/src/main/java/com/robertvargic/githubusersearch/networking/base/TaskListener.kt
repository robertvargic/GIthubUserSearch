package com.robertvargic.githubusersearch.networking.base

interface TaskListener<T> {
    fun onPreExecute()
    fun onSucess(result: T)
    fun onError(error: Throwable)
}