package com.robertvargic.githubusersearch.ui.userdetail

import android.util.Log
import com.robertvargic.githubusersearch.database.UserDao
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.model.Repository
import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.networking.RetrofitUtil
import com.robertvargic.githubusersearch.networking.base.TaskListener
import com.robertvargic.githubusersearch.networking.tasks.GetUserReposTask
import com.robertvargic.githubusersearch.networking.tasks.GetUserTask

class UserDetailPresenter(private var userListSearchView: UserDetailContract.View) : UserDetailContract.Presenter {

    private lateinit var userDao: UserDao

    override fun loadUserDetailsFromDatabase(userId: String, userDatabase: UserRoomDatabase?) {
        userDao = userDatabase!!.userDao()
        userListSearchView.initUserInfo(userDao.getUser(userId))

    }

    override fun loadUserDetailsFromWeb(userId: String) {

        val getUserTask = GetUserTask(RetrofitUtil.createRetrofit(), userId)

        getUserTask.execute(object : TaskListener<User> {
            override fun onPreExecute() {
            }

            override fun onSucess(result: User) {
                userListSearchView.initUserInfo(result)
                loadUserRepos(userId)
            }

            override fun onError(error: Throwable) {
            }
        })
    }

    private fun loadUserRepos(userId: String) {
        val getUserReposTask = GetUserReposTask(RetrofitUtil.createRetrofit(), userId)

        getUserReposTask.execute(object : TaskListener<ArrayList<Repository>> {
            override fun onPreExecute() {

            }

            override fun onSucess(result: ArrayList<Repository>) {
                Log.e("Get User Repo list: ", "Sucess")
                Log.e("Result: ", result.toString())
                userListSearchView.initRepoInfo(result)
            }

            override fun onError(error: Throwable) {
                Log.e("Get User Repo list: ", error.toString())
            }

        })
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}