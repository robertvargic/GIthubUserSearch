package com.robertvargic.githubusersearch.ui.userdetail

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.data.model.Repository
import com.robertvargic.githubusersearch.data.model.User
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.ui.adapters.RepositoryListAdapter
import com.robertvargic.githubusersearch.ui.base.BaseActivity
import com.robertvargic.githubusersearch.util.DATABASE_USERNAME
import com.robertvargic.githubusersearch.util.USERNAME
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : BaseActivity(), UserDetailContract.View {

    private lateinit var userDetailPresenter: UserDetailContract.Presenter
    val database: UserRoomDatabase = UserRoomDatabase.getDatabaseInstance(this)!!

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

    private fun checkIntent() {

        val intent = intent

        if (intent.getStringExtra(USERNAME) != null) {
            favouriteButton.visibility = View.VISIBLE
            favouriteButton.setOnClickListener {
                userDetailPresenter.saveUserToDatabase(database)
                Toast.makeText(this, getString(R.string.user_favourited_user_detail), Toast.LENGTH_LONG).show()
            }
            userDetailPresenter.loadUserDetailsFromWeb(intent.getStringExtra(USERNAME))
        }

        if (intent.getStringExtra(DATABASE_USERNAME) != null) {
            userDetailPresenter.loadUserDetailsFromDatabase(intent.getStringExtra(DATABASE_USERNAME), database)
        }
    }

    override fun initUserInfo(user: User) {
        userNameTextField.text = user.userName
    }

    override fun initRepoInfo(repoList: List<Repository>) {
        val repositoryListAdapter = RepositoryListAdapter(repoList, this)
        recycleView.adapter = repositoryListAdapter
        recycleView.adapter.notifyDataSetChanged()
    }
}