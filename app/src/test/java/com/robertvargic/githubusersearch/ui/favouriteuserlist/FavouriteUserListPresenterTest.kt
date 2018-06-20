package com.robertvargic.githubusersearch.ui.favouriteuserlist

import com.robertvargic.githubusersearch.model.User
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations

class FavouriteUserListPresenterTest {
    lateinit var presenter: FavouriteUserListPresenter

    @Mock
    lateinit var viewContract: FavouriteUserListContract.View

    @Mock
    lateinit var userList: MutableList<User>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = Mockito.spy(FavouriteUserListPresenter(viewContract))
    }

    @Test
    fun `init empty state true`() {
        viewContract.initEmptyState(true)
        verify(viewContract).initEmptyState(true)
        verifyNoMoreInteractions(viewContract)
    }

    @Test
    fun `init empty state false`() {
        viewContract.initEmptyState(false)
        verify(viewContract).initEmptyState(false)
        verifyNoMoreInteractions(viewContract)
    }

    @Test
    fun `init list`(){
        viewContract.initListView(userList)
        verify(viewContract).initListView(userList)
    }

}