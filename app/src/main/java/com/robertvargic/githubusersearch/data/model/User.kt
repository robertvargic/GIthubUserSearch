package com.robertvargic.githubusersearch.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "userTable")
data class User(@PrimaryKey @ColumnInfo(name = "id") val id: String,
                @ColumnInfo(name = "login") var userName: String, //used for getting data for user from endpoints
                @ColumnInfo(name = "avatar_url") val avatarUrl: String?,
                @ColumnInfo(name = "url") val url: String?,
                @ColumnInfo(name = "name") val name: String?,
                @ColumnInfo(name = "bio") val bio: String?,
                @ColumnInfo(name = "public_repos") val numberOfPublicRepos: String?)



