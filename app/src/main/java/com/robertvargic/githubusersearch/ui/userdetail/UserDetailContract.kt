package com.robertvargic.githubusersearch.ui.userdetail

import com.robertvargic.githubusersearch.model.Repository
import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.ui.base.BasePresenter
import com.robertvargic.githubusersearch.ui.base.BaseView

interface UserDetailContract {

    interface View : BaseView<Presenter> {
        fun initUserInfo(user: User)
        fun initRepoInfo(repoList: List<Repository>)
    }

    interface Presenter : BasePresenter {
        fun loadUserDetails(userId: String)
    }
}