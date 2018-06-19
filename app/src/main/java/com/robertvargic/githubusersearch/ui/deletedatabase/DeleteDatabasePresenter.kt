package com.robertvargic.githubusersearch.ui.deletedatabase

import com.robertvargic.githubusersearch.database.UserRoomDatabase
import kotlinx.coroutines.experimental.async

class DeleteDatabasePresenter(private var deleteDatabaseView: DeleteDatabaseContract.View) : DeleteDatabaseContract.Presenter {

    override fun deleteDatabase(userDatabase: UserRoomDatabase?) {
        async {
            userDatabase?.clearAllTables()
        }
        deleteDatabaseView.showToast()
    }

    override fun start() {
    }
}