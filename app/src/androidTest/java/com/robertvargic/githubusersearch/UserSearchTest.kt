package com.robertvargic.githubusersearch

import com.robertvargic.githubusersearch.ui.userlistsearch.UserListSearchContract
import com.robertvargic.githubusersearch.ui.userlistsearch.UserListSearchPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserSearchTest {

    private lateinit var presenter: UserListSearchPresenter

    @Mock lateinit var currencyListView: UserListSearchContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = UserListSearchPresenter(currencyListView)
    }

    @Test
    fun testInitListView() {
        presenter.searchForUser(String.toString())
    }
}