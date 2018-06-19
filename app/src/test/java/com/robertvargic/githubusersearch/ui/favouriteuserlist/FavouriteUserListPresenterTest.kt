package com.robertvargic.githubusersearch.ui.favouriteuserlist

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FavouriteUserListPresenterTest {
    lateinit var presenter: FavouriteUserListPresenter

    @Mock
    lateinit var viewContract: FavouriteUserListContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = Mockito.spy(FavouriteUserListPresenter(viewContract))
    }

}