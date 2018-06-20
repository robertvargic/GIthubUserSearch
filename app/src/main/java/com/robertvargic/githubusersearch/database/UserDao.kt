package com.robertvargic.githubusersearch.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.robertvargic.githubusersearch.data.model.Repository
import com.robertvargic.githubusersearch.data.model.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Query("Select * from userTable")
    fun getAllUsers() : MutableList<User>

    @Query("DELETE from userTable WHERE id = :userId")
    fun deleteUser(userId: String)

    @Query("SELECT * from userTable WHERE id = :userId")
    fun getUser(userId: String): User

    @Delete
    fun delete(user: User)

    @Insert
    fun insert(repositoryList: MutableList<Repository>)

    @Query("SELECT * from repositoryTable WHERE userId = :userId")
    fun getRepoListByUserId(userId: String): MutableList<Repository>


}