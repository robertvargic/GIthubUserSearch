package com.robertvargic.githubusersearch.ui.favouriteuserlist

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.ui.adapters.FavouriteUserAdapter
import com.robertvargic.githubusersearch.ui.adapters.OnUserListItemClickListener
import com.robertvargic.githubusersearch.ui.base.BaseFragment
import com.robertvargic.githubusersearch.ui.userdetail.UserDetailActivity
import com.robertvargic.githubusersearch.util.Constants
import kotlinx.android.synthetic.main.fragment_favourite_user_list.*

class FavouriteUserListFragment: BaseFragment(), FavouriteUserListContract.View, SwipeRefreshLayout.OnRefreshListener {

    override fun onRefresh() {
        favouriteUserListPresenter.getUsersFromDatabase(UserRoomDatabase.getDatabaseInstance(context))
        swipeRefresh.isRefreshing = false
    }

    private lateinit var favouriteUserListPresenter: FavouriteUserListContract.Presenter

    override fun initEmptyState(visible: Boolean) {
        if (visible) {
            emptyStateTextView.visibility = View.VISIBLE
        } else {
            emptyStateTextView.visibility = View.GONE
        }
    }

    override fun initListView(userList: MutableList<User>) {
        val listener = object : OnUserListItemClickListener {
            override fun onClick(userId: String) {
                var intent = Intent()
                intent.setClass(context, UserDetailActivity::class.java)
                intent.putExtra(Constants.DATABASE_USERNAME, userId)
                startActivity(intent)
            }

            override fun onFavouriteClick(user: User) {
                //nothing
            }
        }
        val currencyListAdapter = FavouriteUserAdapter(userList, context, listener)
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
        swipeRefresh.setOnRefreshListener(this)
        recycleView.layoutManager = LinearLayoutManager(context)
        favouriteUserListPresenter.getUsersFromDatabase(UserRoomDatabase.getDatabaseInstance(context))
    }

    override fun setPresenter(presenter: FavouriteUserListContract.Presenter) {
        favouriteUserListPresenter = presenter
    }
}