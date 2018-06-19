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
                favouriteUserView.initEmptyState(true)
                favouriteUserView.initListView(userList)
            } else {
                favouriteUserView.initEmptyState(false)
                favouriteUserView.initListView(userList)
            }
        }
    }

    override fun start() {
    }
}