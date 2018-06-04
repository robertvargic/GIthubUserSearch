package com.robertvargic.githubusersearch.networking.base

interface ServerTask<R> {
    fun execute(listener: TaskListener<R>)
}