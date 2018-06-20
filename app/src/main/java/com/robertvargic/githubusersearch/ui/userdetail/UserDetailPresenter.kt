package com.robertvargic.githubusersearch.ui.userdetail

import android.util.Log
import com.robertvargic.githubusersearch.database.UserDao
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.data.model.Repository
import com.robertvargic.githubusersearch.data.model.User
import com.robertvargic.githubusersearch.data.response.UserResponse
import com.robertvargic.githubusersearch.data.response.mapToUserModel
import com.robertvargic.githubusersearch.networking.RetrofitUtil
import com.robertvargic.githubusersearch.networking.base.TaskListener
import com.robertvargic.githubusersearch.networking.tasks.GetUserReposTask
import com.robertvargic.githubusersearch.networking.tasks.GetUserTask
import kotlinx.coroutines.experimental.async

class UserDetailPresenter(private val userListSearchView: UserDetailContract.View) : UserDetailContract.Presenter {

    private lateinit var userDao: UserDao
    private lateinit var user: User
    private lateinit var userRepoList: MutableList<Repository>

    override fun loadUserDetailsFromWeb(userId: String) {

        val getUserTask = GetUserTask(RetrofitUtil.createRetrofit(), userId)

        getUserTask.execute(object : TaskListener<UserResponse> {
            override fun onPreExecute() {
            }

            override fun onSucess(userResponse: UserResponse) {
                user = userResponse.mapToUserModel()
                userListSearchView.initUserInfo(user)
                loadUserRepos(user.userName)
            }

            override fun onError(error: Throwable) {
            }
        })
    }

    override fun saveUserToDatabase(database: UserRoomDatabase) {
        async {
            database.userDao().insert(user)
            for (repository in userRepoList) {
                repository.userId = user.id
            }
            database.userDao().insert(userRepoList)
        }
    }

    override fun loadUserDetailsFromDatabase(userId: String, userDatabase: UserRoomDatabase?) {
        userDao = userDatabase!!.userDao()
        async {
            val user: User = userDao.getUser(userId)
            val repositoryList = userDao.getRepoListByUserId(userId)
            userListSearchView.initUserInfo(user)
            userListSearchView.initRepoInfo(repositoryList)
        }
    }

    private fun loadUserRepos(userId: String) {
        val getUserReposTask = GetUserReposTask(RetrofitUtil.createRetrofit(), userId)

        getUserReposTask.execute(object : TaskListener<ArrayList<Repository>> {
            override fun onPreExecute() {

            }

            override fun onSucess(result: ArrayList<Repository>) {
                userRepoList = result
                userListSearchView.initRepoInfo(userRepoList)
            }

            override fun onError(error: Throwable) {
                Log.e("Get User Repo list: ", error.toString())
            }

        })
    }

    override fun start() {
    }
}