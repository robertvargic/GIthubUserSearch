package com.robertvargic.githubusersearch.ui.userlistsearch

import android.util.Log
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.model.Repository
import com.robertvargic.githubusersearch.model.SearchResponse
import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.networking.RetrofitUtil
import com.robertvargic.githubusersearch.networking.base.TaskListener
import com.robertvargic.githubusersearch.networking.tasks.GetUserReposTask
import com.robertvargic.githubusersearch.networking.tasks.GetUserTask
import com.robertvargic.githubusersearch.networking.tasks.SearchForUserTask



class UserListSearchPresenter(private var userListSearchView: UserListSearchContract.View) : UserListSearchContract.Presenter {

    lateinit var database: UserRoomDatabase

    override fun saveUserToDatabase(user: User, database: UserRoomDatabase) {
        this.database = database
        saveDetailedUserInfoToDatabase(user.userName)
    }

    override fun start() {

    }

    override fun searchForUser(searchQuery: String) {

        val searchForUserTask = SearchForUserTask(RetrofitUtil.createRetrofit(), searchQuery)

        searchForUserTask.execute(object : TaskListener<SearchResponse> {

            override fun onPreExecute() {
//                userListSearchView.showProgress()
            }

            override fun onSucess(result: SearchResponse) {
                userListSearchView.initListView(result.items)
            }

            override fun onError(error: Throwable) {
//                userListSearchView.initNoResultState()
            }
        })
    }

    private fun saveDetailedUserInfoToDatabase(userId: String) {
        val getUserTask = GetUserTask(RetrofitUtil.createRetrofit(), userId)

        getUserTask.execute(object : TaskListener<User> {
            override fun onPreExecute() {
            }

            override fun onSucess(result: User) {
                database.userDao().insert(result)
                saveUserReposToDatabase(result)
            }

            override fun onError(error: Throwable) {
                Log.e("error", error.localizedMessage)
            }
        })
    }

    private fun saveUserReposToDatabase(user: User) {
        val getUserReposTask = GetUserReposTask(RetrofitUtil.createRetrofit(), user.userName)

        getUserReposTask.execute(object : TaskListener<ArrayList<Repository>> {
            override fun onPreExecute() {

            }

            override fun onSucess(result: ArrayList<Repository>) {
                var repositoriesResult = result

                for (repository in repositoriesResult) {
                    repository.userId = user.id
                }

                database.userDao().insert(repositoriesResult)
            }

            override fun onError(error: Throwable) {

            }

        })
    }


}