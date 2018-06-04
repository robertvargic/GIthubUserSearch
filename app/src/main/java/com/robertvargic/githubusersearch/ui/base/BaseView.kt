package com.robertvargic.githubusersearch.ui.base

interface BaseView<in T> {
    fun setPresenter(presenter: T)
}