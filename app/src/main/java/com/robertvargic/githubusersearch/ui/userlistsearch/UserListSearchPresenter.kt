package com.robertvargic.githubusersearch.ui.userlistsearch

import android.util.Log
import com.robertvargic.githubusersearch.data.model.Repository
import com.robertvargic.githubusersearch.data.model.User
import com.robertvargic.githubusersearch.data.response.*
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.networking.RetrofitUtil
import com.robertvargic.githubusersearch.networking.base.TaskListener
import com.robertvargic.githubusersearch.networking.tasks.GetUserReposTask
import com.robertvargic.githubusersearch.networking.tasks.GetUserTask
import com.robertvargic.githubusersearch.networking.tasks.SearchForUserTask
import kotlinx.coroutines.experimental.async


class UserListSearchPresenter(private val userListSearchView: UserListSearchContract.View) : UserListSearchContract.Presenter {

    lateinit var database: UserRoomDatabase

    override fun searchForUser(searchQuery: String) {

        val searchForUserTask = SearchForUserTask(RetrofitUtil.createRetrofit(), searchQuery)

        searchForUserTask.execute(object : TaskListener<SearchResponse> {

            override fun onPreExecute() {
                userListSearchView.showProgress()
            }

            override fun onSucess(result: SearchResponse) {
                userListSearchView.hideProgress()
                val mutableList: MutableList<User> = ArrayList()
                for (user in result.items) {
                    mutableList.add(user.mapToUserModel())

                }
                checkListSizeAndInit(mutableList)
            }

            override fun onError(error: Throwable) {
                userListSearchView.hideProgress()
            }
        })
    }

    fun checkListSizeAndInit(userList: List<User>) {
        if (userList.isEmpty()) {
            userListSearchView.initResultState("No results")
        } else {
            userListSearchView.initResultState("")
        }
        userListSearchView.initListView(userList)
    }

    override fun saveUserToDatabase(user: User, database: UserRoomDatabase) {
        this.database = database
        saveDetailedUserInfoToDatabase(user.userName)
    }

    private fun saveDetailedUserInfoToDatabase(userId: String) {
        val getUserTask = GetUserTask(RetrofitUtil.createRetrofit(), userId)

        getUserTask.execute(object : TaskListener<UserResponse> {
            override fun onPreExecute() {

            }

            override fun onSucess(result: UserResponse) {
                async { database.userDao().insert(result.mapToUserModel()) }
                saveUserReposToDatabase(result.mapToUserModel())
            }

            override fun onError(error: Throwable) {

                Log.e("error", error.localizedMessage)
            }
        })
    }

    private fun saveUserReposToDatabase(user: User) {
        val getUserReposTask = GetUserReposTask(RetrofitUtil.createRetrofit(), user.userName)

        getUserReposTask.execute(object : TaskListener<ArrayList<RepositoryResponse>> {
            override fun onPreExecute() {

            }

            override fun onSucess(result: ArrayList<RepositoryResponse>) {
                val mutableRepoList: MutableList<Repository> = ArrayList()
                for (repository in result) {
                    repository.userId = user.id
                    mutableRepoList.add(repository.mapToRepositoryModel())
                }

                async { database.userDao().insert(mutableRepoList) }
            }

            override fun onError(error: Throwable) {

            }

        })
    }

    override fun start() {

    }
}