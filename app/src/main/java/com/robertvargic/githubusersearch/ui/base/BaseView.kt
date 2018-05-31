package com.robertvargic.cryptochecker.ui.base

interface BaseView<in T> {
    fun setPresenter(presenter: T)
}