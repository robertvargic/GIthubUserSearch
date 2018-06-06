package com.robertvargic.githubusersearch

import com.robertvargic.githubusersearch.model.SearchResponse
import com.robertvargic.githubusersearch.networking.base.TaskListener
import com.robertvargic.githubusersearch.networking.tasks.SearchForUserTask
import com.robertvargic.githubusersearch.ui.userlistsearch.UserListSearchContract
import com.robertvargic.githubusersearch.ui.userlistsearch.UserListSearchPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserSearchPresenterTest {
    lateinit var presenter: UserListSearchPresenter

    @Mock
    lateinit var searchUserTask: SearchForUserTask

    @Mock
    lateinit var viewContract: UserListSearchContract.View

    @Mock
    lateinit var listener: TaskListener<SearchResponse>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = Mockito.spy(UserListSearchPresenter(viewContract))
    }

    @Test
    fun searchGithubRepos_noQuery() {
        var searchQuery: String = ""
        presenter.searchForUser(searchQuery)
        Mockito.verify(searchUserTask, Mockito.never()).execute(listener)
    }

    @Test
    fun searchGithubRepos() {
        var searchQuery: String = "some query"
        presenter.searchForUser(searchQuery)
        Mockito.verify(searchUserTask, Mockito.never()).execute(listener)
    }

//    @Test
//    fun handleResponse_Sucess() {
//        response: Response: Mocki
//    }
}