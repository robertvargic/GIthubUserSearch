package com.robertvargic.githubusersearch.ui.deletedatabase

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DeleteDatabasePresenterTest {
    lateinit var presenter: DeleteDatabasePresenter

    @Mock
    lateinit var viewContract: DeleteDatabaseContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = Mockito.spy(DeleteDatabasePresenter(viewContract))
    }

    @Test
    fun `test database delete`() {

    }

}