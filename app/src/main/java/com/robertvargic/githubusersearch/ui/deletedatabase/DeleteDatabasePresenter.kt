package com.robertvargic.githubusersearch.ui.deletedatabase

import com.robertvargic.githubusersearch.database.UserRoomDatabase

class DeleteDatabasePresenter(private var deleteDatabaseView: DeleteDatabaseContract.View) : DeleteDatabaseContract.Presenter {

    override fun deleteDatabase(userDatabase: UserRoomDatabase?) {
        userDatabase!!.clearAllTables()
        deleteDatabaseView.showToast()
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}