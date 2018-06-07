package com.robertvargic.githubusersearch.ui.userdetail

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.model.Repository
import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.ui.adapters.RepositoryListAdapter
import com.robertvargic.githubusersearch.ui.base.BaseActivity
import com.robertvargic.githubusersearch.util.Constants
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : BaseActivity(), UserDetailContract.View {

    private lateinit var userDetailPresenter: UserDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        setPresenter(UserDetailPresenter(this))
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        checkIntent()
    }

    override fun setPresenter(presenter: UserDetailContract.Presenter) {
        userDetailPresenter = presenter
    }

    override fun initUserInfo(user: User) {
        userNameTextField.text = user.userName
    }

    override fun initRepoInfo(repoList: List<Repository>) {
        val repositoryListAdapter = RepositoryListAdapter(repoList, this)
        recycleView.adapter = repositoryListAdapter
        recycleView.adapter.notifyDataSetChanged()
    }

    private fun checkIntent() {
        userDetailPresenter.loadUserDetails(intent.getStringExtra(Constants.USERNAME))
    }
}