package com.robertvargic.githubusersearch.ui.userlistsearch

import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.data.model.User
import com.robertvargic.githubusersearch.ui.base.BasePresenter
import com.robertvargic.githubusersearch.ui.base.BaseView

interface UserListSearchContract {

    interface View : BaseView<Presenter> {
        fun initResultState(message: String)
        fun showProgress()
        fun hideProgress()
        fun initListView(userList: MutableList<User>)
        fun saveFavouriteUser(user: User)
    }

    interface Presenter : BasePresenter {
        fun searchForUser(searchQuery: String)
        fun saveUserToDatabase(user: User, database: UserRoomDatabase)
    }
}