package com.robertvargic.githubusersearch.ui.userdetail

import android.os.Bundle
import android.os.PersistableBundle
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.ui.base.BaseActivity

class UserDetailActivity : BaseActivity(), UserDetailContract.View {

    private lateinit var userDetailPresenter: UserDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_user_detail)
        setPresenter(UserDetailPresenter(this))
    }

    override fun setPresenter(presenter: UserDetailContract.Presenter) {
        userDetailPresenter = presenter
    }

    override fun initUserInfo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}