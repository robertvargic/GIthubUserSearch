package com.robertvargic.githubusersearch.ui.userdetail

import android.os.Bundle
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.ui.base.BaseActivity
import com.robertvargic.githubusersearch.util.Constants
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : BaseActivity(), UserDetailContract.View {

    private lateinit var userDetailPresenter: UserDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        setPresenter(UserDetailPresenter(this))
        checkIntent()
    }

    override fun setPresenter(presenter: UserDetailContract.Presenter) {
        userDetailPresenter = presenter
    }

    override fun initUserInfo(user: User) {
        userNameTextField.text = user.userName
    }

    private fun checkIntent() {
        userDetailPresenter.loadUserDetails(intent.getStringExtra(Constants.USERNAME))
    }
}