package com.robertvargic.githubusersearch.ui.deletedatabase

import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.ui.base.BasePresenter
import com.robertvargic.githubusersearch.ui.base.BaseView

interface DeleteDatabaseContract {

    interface View : BaseView<Presenter> {
        fun showToast()
    }

    interface Presenter : BasePresenter {
        fun deleteDatabase(userDatabase: UserRoomDatabase?)
    }
}