package com.robertvargic.githubusersearch

import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.model.Repository
import com.robertvargic.githubusersearch.model.SearchResponse
import com.robertvargic.githubusersearch.model.User
import com.robertvargic.githubusersearch.networking.base.TaskListener
import com.robertvargic.githubusersearch.networking.tasks.GetUserReposTask
import com.robertvargic.githubusersearch.networking.tasks.GetUserTask
import com.robertvargic.githubusersearch.networking.tasks.SearchForUserTask
import com.robertvargic.githubusersearch.ui.userlistsearch.UserListSearchContract
import com.robertvargic.githubusersearch.ui.userlistsearch.UserListSearchPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserListSearchPresenterTest {
    lateinit var presenter: UserListSearchPresenter

    @Mock
    lateinit var searchUserTask: SearchForUserTask

    @Mock
    lateinit var getUserTask: GetUserTask

    @Mock
    lateinit var getUserRepoTask: GetUserReposTask

    @Mock
    lateinit var viewContract: UserListSearchContract.View

    @Mock
    lateinit var listener: TaskListener<SearchResponse>

    @Mock
    lateinit var userDetailListener: TaskListener<User>

    @Mock
    lateinit var userRepoListener: TaskListener<ArrayList<Repository>>

    @Mock
    lateinit var user: User

    @Mock
    lateinit var database: UserRoomDatabase

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
//    fun saveUserToDatabase() {
//        presenter.saveUserToDatabase(user, database)
//        Mockito.verify(getUserTask, Mockito.never()).execute(userDetailListener)
//        Mockito.verify(getUserRepoTask, Mockito.never()).execute(userRepoListener)
//    }

//    @Test
//    fun handleResponse_Sucess() {
//        response: Response: Mocki
//    }
}