package com.robertvargic.githubusersearch.ui.userlistsearch

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.ui.adapters.OnSearchUserClickListener
import com.robertvargic.githubusersearch.ui.adapters.UserListAdapter
import com.robertvargic.githubusersearch.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_list_search.*

class UserListSearchFragment: BaseFragment(), UserListSearchContract.View {

    var database: UserRoomDatabase = UserRoomDatabase.getDatabaseInstance(context)!!

    override fun saveFavouriteUser(user: User) {
        println(Toast.makeText(context, "User favourited", Toast.LENGTH_LONG).show())
        database.userDao().insert(user)
    }

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
        val listener = object : OnSearchUserClickListener {
            override fun onFavouriteClick(user: User) {
                saveFavouriteUser(user)
            }
        }
        val currencyListAdapter = UserListAdapter(userList, context, listener)
        recycleView.adapter = currencyListAdapter
        recycleView.adapter.notifyDataSetChanged()

//        var itemTouchHelper = ItemTouchHelper(SwipeController())
//        itemTouchHelper.attachToRecyclerView(recycleView)

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
        recycleView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
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
