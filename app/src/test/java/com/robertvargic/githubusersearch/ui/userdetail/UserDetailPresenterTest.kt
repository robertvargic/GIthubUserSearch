package com.robertvargic.githubusersearch.ui.userdetail

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserDetailPresenterTest {
    lateinit var presenter: UserDetailPresenter
    @Mock
    lateinit var viewContract: UserDetailContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = Mockito.spy(UserDetailPresenter(viewContract))
    }

}