package com.robertvargic.githubusersearch.ui.userlistsearch

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.ui.adapters.UserListAdapter
import com.robertvargic.githubusersearch.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_list_search.*

class UserListSearchFragment: BaseFragment(), UserListSearchContract.View {

    private lateinit var userListSearchPresenter: UserListSearchContract.Presenter

    override fun initEmptyState() {

    }

    override fun initNoResultState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initListView(userList: MutableList<User>) {
        val currencyListAdapter = UserListAdapter(userList, context)
        recycleView.adapter = currencyListAdapter
        recycleView.adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter(UserListSearchPresenter(this))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_user_list_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView.layoutManager = LinearLayoutManager(context)
        init()
        initEmptyState()

    }

    override fun setPresenter(presenter: UserListSearchContract.Presenter) {
        userListSearchPresenter = presenter
    }

    fun init() {
        searchButton.setOnClickListener { userListSearchPresenter.searchForUser(searchEditText.text.toString()) }
    }
}
