package com.robertvargic.githubusersearch.ui.bottomnavigation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.ui.adapters.ViewPagerAdapter
import com.robertvargic.githubusersearch.ui.base.BaseActivity
import com.robertvargic.githubusersearch.ui.deletedatabase.DeleteDatabaseFragment
import com.robertvargic.githubusersearch.ui.favouriteuserlist.FavouriteUserListFragment
import com.robertvargic.githubusersearch.ui.userlistsearch.UserListSearchFragment
import com.robertvargic.githubusersearch.util.DELETE_DATABASE_FRAGMENT
import com.robertvargic.githubusersearch.util.FAVOURITE_USERS_FRAGMENT
import com.robertvargic.githubusersearch.util.SEARCH_USER_FRAGMENT
import kotlinx.android.synthetic.main.activity_bottom_navigation_bar.*

class BottomNavigationBarActivity : BaseActivity() {

    var searchFragment = UserListSearchFragment()
    var favouriteFragment = FavouriteUserListFragment()
    var deleteFragment = DeleteDatabaseFragment()
    var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_bar)
        navigation.setOnNavigationItemSelectedListener(navigationListener)
        setupViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        var viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(searchFragment, SEARCH_USER_FRAGMENT)
        viewPagerAdapter.addFragment(favouriteFragment, FAVOURITE_USERS_FRAGMENT)
        viewPagerAdapter.addFragment(deleteFragment, DELETE_DATABASE_FRAGMENT)

        viewPager.adapter = viewPagerAdapter

        viewPager.addOnPageChangeListener(onPageChangeListener)

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

    private val onPageChangeListener = object: ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            if (prevMenuItem != null) {
                prevMenuItem!!.isChecked = false
            } else {
                navigation.menu.getItem(0).isChecked = false
            }
            navigation.menu.getItem(position).isChecked = true
            prevMenuItem = navigation.menu.getItem(position)
        }
    }
}
