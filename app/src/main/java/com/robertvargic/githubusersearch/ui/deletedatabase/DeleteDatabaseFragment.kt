package com.robertvargic.githubusersearch.ui.deletedatabase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.database.UserRoomDatabase
import com.robertvargic.githubusersearch.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_delete_database.*

class DeleteDatabaseFragment : BaseFragment(), DeleteDatabaseContract.View {

    val database: UserRoomDatabase = UserRoomDatabase.getDatabaseInstance(context)!!

    private lateinit var deleteDatabasePresenter: DeleteDatabaseContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter(DeleteDatabasePresenter(this))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_delete_database, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deleteButton.setOnClickListener { deleteDatabasePresenter.deleteDatabase(database) }
    }

    override fun setPresenter(presenter: DeleteDatabaseContract.Presenter) {
        deleteDatabasePresenter = presenter
    }

    override fun showToast() {
        Toast.makeText(context, getString(R.string.users_deleted), Toast.LENGTH_LONG).show()
    }
}