package com.robertvargic.githubusersearch.ui.userdetail

import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.data.model.Repository
import com.robertvargic.githubusersearch.data.model.User
import com.robertvargic.githubusersearch.ui.base.BasePresenter
import com.robertvargic.githubusersearch.ui.base.BaseView

interface UserDetailContract {

    interface View : BaseView<Presenter> {
        fun initUserInfo(user: User)
        fun initRepoInfo(repoList: List<Repository>)
    }

    interface Presenter : BasePresenter {
        fun loadUserDetailsFromWeb(userId: String)
        fun loadUserDetailsFromDatabase(userId: String, userDatabase: UserRoomDatabase?)
        fun saveUserToDatabase(database: UserRoomDatabase)
    }
}