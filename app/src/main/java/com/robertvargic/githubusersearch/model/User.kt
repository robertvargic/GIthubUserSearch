package com.robertvargic.githubusersearch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "userTable")
data class User(@PrimaryKey @ColumnInfo(name = "id") val id: String,
                @Json("login") @ColumnInfo(name = "login") var userName: String,
                @Json("avatar_url") @ColumnInfo(name = "avatar_url") val avatarUrl: String?,
                @Json("html_url") @ColumnInfo(name = "url") val url: String?,
                @Json("name") @ColumnInfo(name = "name") val name: String?,
                @ColumnInfo(name = "bio") val bio: String?,
                @Json("public_repos") @ColumnInfo(name = "public_repos") val numberOfPublicRepos: String?) {
}

