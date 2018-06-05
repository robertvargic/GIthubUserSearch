package com.robertvargic.githubusersearch.ui.userdetail

import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.networking.RetrofitUtil
import com.robertvargic.githubusersearch.networking.base.TaskListener
import com.robertvargic.githubusersearch.networking.tasks.GetUserTask

class UserDetailPresenter(private var userListSearchView: UserDetailContract.View): UserDetailContract.Presenter {


    override fun loadUserDetails(userId: String) {

        val getUserTask = GetUserTask(RetrofitUtil.createRetrofit(), userId)

        getUserTask.execute(object : TaskListener<User> {
            override fun onPreExecute() {
            }

            override fun onSucess(result: User) {
                userListSearchView.initUserInfo(result)
            }

            override fun onError(error: Throwable) {
            }
        })
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}