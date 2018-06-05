package com.robertvargic.githubusersearch.ui.favouriteuserlist

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.ui.adapters.OnSearchUserClickListener
import com.robertvargic.githubusersearch.ui.adapters.UserListAdapter
import com.robertvargic.githubusersearch.ui.base.BaseFragment
import com.robertvargic.githubusersearch.ui.userdetail.UserDetailActivity
import com.robertvargic.githubusersearch.util.Constants
import kotlinx.android.synthetic.main.fragment_user_list_search.*

class FavouriteUserListFragment: BaseFragment(), FavouriteUserListContract.View {

    private lateinit var favouriteUserListPresenter: FavouriteUserListContract.Presenter

    override fun initEmptyState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initListView(userList: MutableList<User>) {
        val listener = object : OnSearchUserClickListener {
            override fun onClick(userName: String) {
                var intent = Intent()
                intent.setClass(context, UserDetailActivity::class.java)
                intent.putExtra(Constants.USERNAME, userName)
                startActivity(intent)
            }

            override fun onFavouriteClick(user: User) {
                //nothing
            }
        }
        val currencyListAdapter = UserListAdapter(userList, context, listener)
        recycleView.adapter = currencyListAdapter
        recycleView.adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter(FavouriteUserListPresenter(this))
//        favouriteUserListPresenter.getUsersFromDatabase(context?.let { GithubUserSearchApplication.getInstance(it).userDatabase })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_favourite_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView.layoutManager = LinearLayoutManager(context)
        favouriteUserListPresenter.getUsersFromDatabase(UserRoomDatabase.getDatabaseInstance(context))
    }

    override fun setPresenter(presenter: FavouriteUserListContract.Presenter) {
        favouriteUserListPresenter = presenter
    }
}