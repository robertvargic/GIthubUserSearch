package com.robertvargic.githubusersearch.ui.userdetail

import com.robertvargic.githubusersearch.ui.base.BasePresenter
import com.robertvargic.githubusersearch.ui.base.BaseView

interface UserDetailContract {

    interface View : BaseView<Presenter> {
        fun initUserInfo()
    }

    interface Presenter : BasePresenter {
        fun loadUserDetails(userId: String)
    }
}