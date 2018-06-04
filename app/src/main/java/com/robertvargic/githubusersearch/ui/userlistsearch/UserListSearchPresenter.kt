package com.robertvargic.githubusersearch.ui.userlistsearch

import com.robertvargic.githubusersearch.model.SearchResponse
import com.robertvargic.githubusersearch.networking.RetrofitUtil
import com.robertvargic.githubusersearch.networking.base.TaskListener
import com.robertvargic.githubusersearch.networking.tasks.SearchForUserTask

class UserListSearchPresenter(private var userListSearchView: UserListSearchContract.View) : UserListSearchContract.Presenter {

    override fun start() {

    }

    override fun searchForUser(searchQuery: String) {
//        var retrofit: Retrofit = RetrofitUtil.createRetrofit()
//        var apiService: ApiService = retrofit.create(ApiService::class.java)
//        var call = apiService.searchUsers(searchQuery)
//
//        ApiInteractor(apiService).searchUsers(searchQuery, call)
//
//        apiService.searchUsers(searchQuery)

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
}