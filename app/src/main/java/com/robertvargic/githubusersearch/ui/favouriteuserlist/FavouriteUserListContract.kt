package com.robertvargic.githubusersearch.ui.favouriteuserlist

import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.data.model.User
import com.robertvargic.githubusersearch.ui.base.BasePresenter
import com.robertvargic.githubusersearch.ui.base.BaseView

interface FavouriteUserListContract {

    interface View : BaseView<Presenter> {
        fun initEmptyState(visible: Boolean)
        fun initListView(userList: MutableList<User>)
    }

    interface Presenter : BasePresenter {
        fun getUsersFromDatabase(userDatabase: UserRoomDatabase?)
    }
}