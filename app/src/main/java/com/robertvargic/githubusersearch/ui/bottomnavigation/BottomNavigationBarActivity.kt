package com.robertvargic.githubusersearch.ui.bottomnavigation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.ui.adapters.ViewPagerAdapter
import com.robertvargic.githubusersearch.ui.base.BaseActivity
import com.robertvargic.githubusersearch.ui.deletedatabase.DeleteDatabaseFragment
import com.robertvargic.githubusersearch.ui.favouriteuserlist.FavouriteUserListFragment
import com.robertvargic.githubusersearch.ui.userlistsearch.UserListSearchFragment
import com.robertvargic.githubusersearch.util.Constants
import kotlinx.android.synthetic.main.activity_bottom_navigation_bar.*

class BottomNavigationBarActivity : BaseActivity() {

    var searchFragment = UserListSearchFragment()
    var favouriteFragment = FavouriteUserListFragment()
    var deleteFragment = DeleteDatabaseFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_bar)
        navigation.setOnNavigationItemSelectedListener(navigationListener)
        setupViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(searchFragment, Constants.SEARCH_USER_FRAGMENT)
        viewPagerAdapter.addFragment(favouriteFragment, Constants.FAVOURITE_USERS_FRAGMENT)
        viewPagerAdapter.addFragment(deleteFragment, Constants.DELETE_DATABASE_FRAGMENT)

        viewPager.adapter = viewPagerAdapter
    }

    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_search -> {
                viewPager.setCurrentItem(0, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favourites -> {
                viewPager.setCurrentItem(1, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_delete -> {
                viewPager.setCurrentItem(2, true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
