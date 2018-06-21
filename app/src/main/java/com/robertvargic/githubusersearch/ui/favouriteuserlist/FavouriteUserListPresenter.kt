package com.robertvargic.githubusersearch.ui.favouriteuserlist

import com.robertvargic.githubusersearch.database.UserDao
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import kotlinx.coroutines.experimental.async

class FavouriteUserListPresenter(private var favouriteUserView: FavouriteUserListContract.View) : FavouriteUserListContract.Presenter {

    private lateinit var userDao: UserDao

    override fun getUsersFromDatabase(userDatabase: UserRoomDatabase?) {
        userDao = userDatabase!!.userDao()
        async {
            val userList = userDao.getAllUsers()
            if (userList.size == 0) {
                favouriteUserView.initListView(userList)
                favouriteUserView.initEmptyState(true)
            } else {
                favouriteUserView.initListView(userList)
                favouriteUserView.initEmptyState(false)
            }
        }
    }

    override fun start() {
    }
}