package com.robertvargic.githubusersearch.ui.userlistsearch

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.ui.adapters.OnUserListItemClickListener
import com.robertvargic.githubusersearch.ui.adapters.UserListAdapter
import com.robertvargic.githubusersearch.ui.base.BaseFragment
import com.robertvargic.githubusersearch.ui.userdetail.UserDetailActivity
import com.robertvargic.githubusersearch.util.USERNAME
import kotlinx.android.synthetic.main.fragment_user_list_search.*

class UserListSearchFragment : BaseFragment(), UserListSearchContract.View {

    var database: UserRoomDatabase = UserRoomDatabase.getDatabaseInstance(context)!!
    private lateinit var userListSearchPresenter: UserListSearchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter(UserListSearchPresenter(this))
    }

    override fun setPresenter(presenter: UserListSearchContract.Presenter) {
        userListSearchPresenter = presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_user_list_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        initListeners()
        initResultState(getString(R.string.result_state_start_search))
    }

    private fun initListeners() {
        searchButton.setOnClickListener {
            if (isNetworkConnected()) {
                userListSearchPresenter.searchForUser(searchEditText.text.toString())
            } else {
                Toast.makeText(context, getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
            }
        }

        searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (isNetworkConnected()) {
                    userListSearchPresenter.searchForUser(searchEditText.text.toString())
                } else {
                    Toast.makeText(context, getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
                }
                return@OnEditorActionListener true
            }
            false
        })
    }

    override fun initListView(userList: MutableList<User>) {
        val listener = object : OnUserListItemClickListener {
            override fun onClick(userName: String) {
                val intent = Intent()
                intent.setClass(context, UserDetailActivity::class.java)
                intent.putExtra(USERNAME, userName)
                startActivity(intent)
            }

            override fun onFavouriteClick(user: User) {
                saveFavouriteUser(user)
            }
        }
        val currencyListAdapter = UserListAdapter(userList, context, listener)
        recycleView.adapter = currencyListAdapter
        recycleView.adapter.notifyDataSetChanged()
    }

    override fun initResultState(message: String) {
        if (message == "") {
            listStateTextView.visibility = View.GONE
        } else {
            listStateTextView.visibility = View.VISIBLE
            listStateTextView.text = message
        }
    }

    override fun saveFavouriteUser(user: User) {
        Toast.makeText(context, getString(R.string.user_favourited), Toast.LENGTH_LONG).show()
        userListSearchPresenter.saveUserToDatabase(user, database)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    private fun isNetworkConnected(): Boolean {
        val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }
}


