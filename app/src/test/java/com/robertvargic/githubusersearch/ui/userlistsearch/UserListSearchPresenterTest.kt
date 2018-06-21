package com.robertvargic.githubusersearch

import com.robertvargic.githubusersearch.data.model.Repository
import com.robertvargic.githubusersearch.data.model.User
import com.robertvargic.githubusersearch.data.response.SearchResponse
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.networking.base.TaskListener
import com.robertvargic.githubusersearch.networking.tasks.GetUserReposTask
import com.robertvargic.githubusersearch.networking.tasks.GetUserTask
import com.robertvargic.githubusersearch.networking.tasks.SearchForUserTask
import com.robertvargic.githubusersearch.ui.userlistsearch.UserListSearchContract
import com.robertvargic.githubusersearch.ui.userlistsearch.UserListSearchPresenter
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
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
    fun `search github repos no query`() {
        var searchQuery: String = ""
        presenter.searchForUser(searchQuery)
        Mockito.verifyNoMoreInteractions(searchUserTask)
    }

    @Test
    fun `search github repos`() {
        var searchQuery: String = "some query"
        presenter.searchForUser(searchQuery)
        Mockito.verify(searchUserTask, Mockito.never()).execute(listener)
    }

    @Test
    fun `init result state`() {
        val someString = "some string"
        viewContract.initResultState(someString)
        verify(viewContract).initResultState(someString)
        verifyNoMoreInteractions(viewContract)
    }

    @Test
    fun `init result state empty`() {
        val emptyString = ""
        viewContract.initResultState(emptyString)
        verify(viewContract).initResultState(emptyString)
        verifyNoMoreInteractions(viewContract)
    }

    @Test
    fun `show progress`() {
        viewContract.showProgress()
        verify(viewContract).showProgress()
        verifyNoMoreInteractions(viewContract)
    }

    @Test
    fun `hide progress`() {
        viewContract.hideProgress()
        verify(viewContract).hideProgress()
        verifyNoMoreInteractions(viewContract)
    }

    @Test
    fun `init list view empty`() {
        val userList = arrayListOf<User>()

        presenter.checkListSizeAndInit(userList)
        verify(viewContract).initListView(userList)
        verify(viewContract).initResultState("No results")
        verifyNoMoreInteractions(viewContract)
    }

    @Test
    fun `init list view not empty`() {
        val userList = arrayListOf<User>()
        userList.add(User("123", "misko", null, null, null, null, null))

        presenter.checkListSizeAndInit(userList)
        verify(viewContract).initListView(userList)
        verify(viewContract).initResultState("")
        verifyNoMoreInteractions(viewContract)
    }

    @After
    fun validate() {
        validateMockitoUsage()
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