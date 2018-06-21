package com.robertvargic.githubusersearch.ui.userdetail

import com.robertvargic.githubusersearch.data.model.Repository
import com.robertvargic.githubusersearch.data.model.User
import com.robertvargic.githubusersearch.data.response.RepositoryResponse
import com.robertvargic.githubusersearch.networking.base.TaskListener
import com.robertvargic.githubusersearch.networking.tasks.GetUserReposTask
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations

class UserDetailPresenterTest {
    lateinit var presenter: UserDetailPresenter

    @Mock
    lateinit var viewContract: UserDetailContract.View

    @Mock
    lateinit var getUserReposTask: GetUserReposTask

    @Mock
    lateinit var listener: TaskListener<ArrayList<RepositoryResponse>>

    @Mock
    lateinit var user: User

    @Mock
    lateinit var repoList: ArrayList<Repository>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = Mockito.spy(UserDetailPresenter(viewContract))
    }

    @Test
    fun `get user details from web`() {
        presenter.loadUserDetailsFromWeb("string")
        verify(getUserReposTask, Mockito.never()).execute(listener)
    }

    @Test
    fun `init user info`() {
        viewContract.initUserInfo(user)
        verify(viewContract).initUserInfo(user)
        verifyNoMoreInteractions(viewContract)
    }

    @Test
    fun `init repo info`() {
        viewContract.initRepoInfo(repoList)
        verify(viewContract).initRepoInfo(repoList)
        verifyNoMoreInteractions(viewContract)
    }

}