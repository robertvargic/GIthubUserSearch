package com.robertvargic.githubusersearch.ui.favouriteuserlist

import com.robertvargic.githubusersearch.database.UserDao
import com.robertvargic.githubusersearch.database.UserRoomDatabase

class FavouriteUserListPresenter(var favouriteUserView: FavouriteUserListContract.View) : FavouriteUserListContract.Presenter {

    private lateinit var userDao: UserDao

    override fun getUsersFromDatabase(userDatabase: UserRoomDatabase?) {
        userDao = userDatabase!!.userDao()
        favouriteUserView.initListView(userDao.getAllUsers())
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}